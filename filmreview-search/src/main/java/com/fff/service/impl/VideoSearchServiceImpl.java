package com.fff.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fff.Responses.VideoResponse;
import com.fff.dao.VideoInterface;
import com.fff.domain.Video;
import com.fff.service.VideoSearchService;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class VideoSearchServiceImpl implements VideoSearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    
    @Autowired
    private VideoInterface videoInterface;
    
    private Logger logger = LoggerFactory.getLogger(VideoSearchServiceImpl.class);
    @Override
    public Boolean createIndex() {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("video");
        createIndexRequest.settings(Settings.builder().put("number_of_shards",1).put("number_of_replicas",0));
        createIndexRequest.mapping("doc","{\n" +
                "    \"properties\":{\n" +
                "        \"videoId\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"index\":false\n" +
                "        },\n" +
                "        \"videoName\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"analyzer\":\"ik_max_word\",\n" +
                "            \"search_analyzer\":\"ik_smart\"\n" +
                "        },\n" +
                "        \"videoDirector\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"analyzer\":\"ik_max_word\",\n" +
                "            \"search_analyzer\":\"ik_smart\"\n" +
                "        },\n" +
                "        \"videoRole\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"analyzer\":\"ik_max_word\",\n" +
                "            \"search_analyzer\":\"ik_smart\"\n" +
                "        },\n" +
                "        \"videoProducer\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"analyzer\":\"ik_max_word\",\n" +
                "            \"search_analyzer\":\"ik_smart\"\n" +
                "        },\n" +
                "        \"videoReview\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"index\":false\n" +
                "        },\n" +
                "        \"videoLanguage\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"analyzer\":\"ik_max_word\",\n" +
                "            \"search_analyzer\":\"ik_smart\"\n" +
                "        },\n" +
                "        \"videoGrade\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"index\":false\n" +
                "        },\n" +
                "        \"videoShowTime\":{\n" +
                "            \"type\":\"date\",\n" +
                "            \"format\":\"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\"\n" +
                "        },\n" +
                "        \"videoLength\":{\n" +
                "            \"type\":\"text\"\n" +
                "        },\n" +
                "        \"classify\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"analyzer\":\"ik_max_word\",\n" +
                "            \"search_analyzer\":\"ik_smart\"\n" +
                "        },\n" +
                "        \"videoType\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"analyzer\":\"ik_max_word\",\n" +
                "            \"search_analyzer\":\"ik_smart\"\n" +
                "        },\n" +
                "        \"visible\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"index\":false\n" +
                "        },\n" +
                "        \"showIndex\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"index\":false\n" +
                "        },\n" +
                "        \"videoPic\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"index\":false\n" +
                "        },\n" +
                "        \"collectionOrsubscription\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"index\":false\n" +
                "        },\n" +
                "        \"videoStatus\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"index\":false\n" +
                "        },\n" +
                "        \"createTime\":{\n" +
                "            \"type\":\"date\",\n" +
                "            \"format\":\"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\"\n" +
                "        },\n" +
                "        \"videoUrl\":{\n" +
                "            \"type\":\"text\",\n" +
                "            \"index\":false\n" +
                "        },\n" +
                "        \"studymodel\":{\n" +
                "            \"type\":\"keyword\"\n" +
                "        },\n" +
                "        \"timestamp\":{\n" +
                "            \"type\":\"date\",\n" +
                "            \"format\":\"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\"\n" +
                "        }\n" +
                "    }\n" +
                "}",XContentType.JSON);
        IndicesClient indices = restHighLevelClient.indices();
        CreateIndexResponse response = null;
        try {
            response = indices.create(createIndexRequest);
            logger.info("创建索引结果为：" + response.isAcknowledged());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.isAcknowledged()){
            return true;
        }
        return false;
    }

    @Override
    public void toEs() {
        String videoData = videoInterface.getVideoData();
        if (videoData != null){
            List<Video> videoList = JSONObject.parseArray(videoData, Video.class);
            for (Video video : videoList) {
                IndexRequest indexRequest = new IndexRequest("video", "doc", video.getVideoId() + "");
                String jsonString = JSON.toJSONString(video);
                indexRequest.source(jsonString, XContentType.JSON);
                IndexResponse response = null;
                try {
                    response = restHighLevelClient.index(indexRequest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!response.isFragment()){

                }
            }
        }
    }

    @Override
    @RabbitListener(queues = "video.save")
    public void saveVideoToEs(Video video) {
        IndexRequest indexRequest = new IndexRequest("video", "doc", video.getVideoId() + "");
        String jsonString = JSON.toJSONString(video);
        indexRequest.source(jsonString,XContentType.JSON);
        IndexResponse response = null;
        try {
            response = restHighLevelClient.index(indexRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
       logger.info(response.isFragment()+"添加------>");
    }

    @Override
    @RabbitListener(queues = "video.delete")
    public void deleteVideoFromEs(Integer id) {
        DeleteRequest deleteRequest = new DeleteRequest("video", "doc", id + "");
        DeleteResponse response = null;
        try {
            response = restHighLevelClient.delete(deleteRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
      logger.info(!response.isFragment()+"删除--------->");
    }

    @Override
    @RabbitListener(queues = "video.update")
    public void updateVideoInEs(Video video) {
        UpdateRequest updateRequest = new UpdateRequest("video", "doc", video.getVideoId() + "");
        String jsonString = JSON.toJSONString(video);
        updateRequest.doc(jsonString, XContentType.JSON);
        UpdateResponse response = null;
        try {
            response = restHighLevelClient.update(updateRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
       logger.info(!response.isFragment()+"修改-------->");
    }

    @Override
    public VideoResponse selectVideoByPage(Integer page, Integer size, String keys) {
        SearchRequest searchRequest = new SearchRequest("video");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(size);
        searchSourceBuilder.from((page-1)*size);
        MultiMatchQueryBuilder multiMatchQueryBuilder =
                QueryBuilders.multiMatchQuery(keys,"videoName", "videoDirector", "videoRole", "videoProducer", "videoLanguage", "videoType");
        SearchSourceBuilder sourceBuilder = searchSourceBuilder.query(multiMatchQueryBuilder.field("videoName",10));
        searchRequest.source(sourceBuilder);
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null){
            SearchHits searchHits = response.getHits();
            long totalHits = searchHits.totalHits;
            SearchHit[] hits = searchHits.getHits();
            List<Video> videoList = new ArrayList<>();
            for (SearchHit hit : hits) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Object json = JSON.toJSON(sourceAsMap);
                Video video = JSONObject.toJavaObject((JSON) json, Video.class);
                videoList.add(video);
            }
            VideoResponse videoResponse = new VideoResponse();
            videoResponse.setVideoList(videoList);
            videoResponse.setVideoTotal(totalHits);
            return videoResponse;
        }
        return null;
    }

    @Override
    public VideoResponse findVideoByClassify(Integer page, Integer size, String key) {
        SearchRequest searchRequest = new SearchRequest("video");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(size);
        searchSourceBuilder.from((page-1)*size);
        searchSourceBuilder.query(QueryBuilders.termQuery("classify",key));
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null){
            SearchHits searchHits = response.getHits();
            long totalHits = searchHits.totalHits;
            SearchHit[] hits = searchHits.getHits();
            List<Video> videoList = new ArrayList<>();
            for (SearchHit hit : hits) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Object json = JSON.toJSON(sourceAsMap);
                Video video = JSONObject.toJavaObject((JSON) json, Video.class);
                videoList.add(video);
            }
            VideoResponse videoResponse = new VideoResponse();
            videoResponse.setVideoList(videoList);
            videoResponse.setVideoTotal(totalHits);
            return videoResponse;
        }
        return null;
    }

    @Override
    public VideoResponse findVideoByType(Integer page, Integer size, String classifyKey, String typeKey) {
        SearchRequest searchRequest = new SearchRequest("video");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(size);
        searchSourceBuilder.from((page-1)*size);
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("classify", classifyKey);
        MultiMatchQueryBuilder matchQueryBuilder = QueryBuilders.multiMatchQuery(typeKey, "videoType");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(queryBuilder);
        boolQueryBuilder.must(matchQueryBuilder);
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null){
            SearchHits searchHits = response.getHits();
            long totalHits = searchHits.totalHits;
            SearchHit[] hits = searchHits.getHits();
            List<Video> videoList = new ArrayList<>();
            for (SearchHit hit : hits) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Object json = JSON.toJSON(sourceAsMap);
                Video video = JSONObject.toJavaObject((JSON) json, Video.class);
                videoList.add(video);
            }
            VideoResponse videoResponse = new VideoResponse();
            videoResponse.setVideoList(videoList);
            videoResponse.setVideoTotal(totalHits);
            return videoResponse;
        }

        return null;
    }
}
