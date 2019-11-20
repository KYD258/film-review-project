package com.fff.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fff.Responses.CommodityResponse;
import com.fff.dao.CommodityInterface;
import com.fff.domain.Commodity;
import com.fff.service.CommoditySearchService;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommoditySearchServiceImpl implements CommoditySearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private CommodityInterface commodityInterface;

    @Override
    public void createIndex() {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("commodity");
        createIndexRequest.settings(Settings.builder().put("number_of_shards",1).put("number_of_replicas",0));
        createIndexRequest.mapping("doc","{\n" +
                "                \"properties\": {\n" +
                "                    \"commodityId\": {\n" +
                "                        \"type\": \"text\"\n" +
                "                    },\n" +
                "                    \"commodityName\": {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"analyzer\": \"ik_max_word\",\n" +
                "                        \"search_analyzer\": \"ik_smart\"\n" +
                "                    },\n" +
                "                    \"commodityPic\":{\n" +
                "                        \"type\":\"text\",\n" +
                "                        \"index\":false\n" +
                "                    },\n" +
                "                    \"description\": {\n" +
                "                        \"type\": \"text\",\n" +
                "                        \"analyzer\": \"ik_max_word\",\n" +
                "                        \"search_analyzer\": \"ik_smart\"\n" +
                "                    },\n" +
                "                    \"commodityPrice\": {\n" +
                "                        \"type\": \"double\"\n" +
                "                    },\n" +
                "                    \"commodityNum\": {\n" +
                "                        \"type\": \"text\"\n" +
                "                    },\n" +
                "                    \"addTime\": {\n" +
                "                        \"type\": \"date\",\n" +
                "                        \"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\"\n" +
                "                    },\n" +
                "                    \"outTime\": {\n" +
                "                        \"type\": \"date\",\n" +
                "                        \"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\"\n" +
                "                    }\n" +
                "                }\n" +
                "            }",XContentType.JSON);
        IndicesClient indices = restHighLevelClient.indices();
        try {
            CreateIndexResponse createIndexResponse = indices.create(createIndexRequest);
            boolean acknowledged = createIndexResponse.isAcknowledged();
            if (acknowledged){
                System.out.println("创建索引结果为："+acknowledged);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toEs() {
        String commodityData = commodityInterface.getCommodityData();
        if (commodityData != null){
            List<Commodity> commodityList = JSONObject.parseArray(commodityData,Commodity.class);
            for (Commodity commodity : commodityList) {
                IndexRequest indexRequest = new IndexRequest("commodity", "doc", commodity.getCommodityId() + "");
                String jsonString = JSON.toJSONString(commodity);
                indexRequest.source(jsonString,XContentType.JSON);
                try {
                    IndexResponse response = restHighLevelClient.index(indexRequest);
                    DocWriteResponse.Result result = response.getResult();
                    System.out.println(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public Boolean saveCommodityToEs(Commodity commodity) {
        IndexRequest indexRequest = new IndexRequest("commodity", "doc", commodity.getCommodityId() + "");
        String jsonString = JSON.toJSONString(commodity);
        indexRequest.source(jsonString,XContentType.JSON);
        IndexResponse response = null;
        try {
            response = restHighLevelClient.index(indexRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        DocWriteResponse.Result result = response.getResult();
        boolean fragment = response.isFragment();
//        System.out.println(result);
        if (!response.isFragment()){
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteCommodityFromEs(Integer id) {
        DeleteRequest deleteRequest = new DeleteRequest("commodity", "doc", id + "");
        DeleteResponse response = null;
        try {
            response = restHighLevelClient.delete(deleteRequest);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
        if (!response.isFragment()){
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateCommodityInEs(Commodity commodity) throws IOException, InterruptedException {
        UpdateRequest updateRequest = new UpdateRequest("commodity", "doc", commodity.getCommodityId() + "");
        String jsonString = JSON.toJSONString(commodity);
        updateRequest.doc(jsonString,XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(updateRequest);
        if (!response.isFragment()){
            return true;
        }
        return false;
    }

    @Override
    public CommodityResponse selectCommodityByPage(Integer page, Integer size, String keys) {
        SearchRequest searchRequest = new SearchRequest("commodity");
        searchRequest.types("doc");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        int from = (page-1)*size;
        sourceBuilder.size(size);
        sourceBuilder.from(from);

        sourceBuilder.query(QueryBuilders.multiMatchQuery(keys,"commodityName","description").field("commodityName",10));
        searchRequest.source(sourceBuilder);
        sourceBuilder.sort("outTime",SortOrder.DESC);
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHits responseHits = response.getHits();
        SearchHit[] hits = responseHits.getHits();
        long totalHits = responseHits.totalHits;
        List<Commodity> list = new ArrayList<>();
        for (SearchHit searchHit : hits) {
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            Object json = JSON.toJSON(sourceAsMap);
            Commodity commodity = JSONObject.toJavaObject((JSON) json, Commodity.class);
            list.add(commodity);
        }
        CommodityResponse commodityResponse = new CommodityResponse();
        commodityResponse.setCommodityList(list);
        commodityResponse.setTotal(totalHits);
        return commodityResponse;
    }
}
