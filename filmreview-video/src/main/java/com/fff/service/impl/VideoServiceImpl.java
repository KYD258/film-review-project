package com.fff.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fff.Responses.VideoResponse;
import com.fff.dao.VideoRepository;
import com.fff.domain.Video;
import com.fff.service.VideoService;
import com.fff.utils.RedisUtils;
import com.fff.utils.UploadUtils;
import com.fff.utils.VideoUploadUtils;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VideoServiceImpl implements VideoService, RabbitTemplate.ConfirmCallback {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UploadUtils uploadUtils;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private VideoUploadUtils videoUploadUtils;

    private Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);
    @Override
    public Boolean saveVideo(Video video) {
        video.setCreateTime(new Date());
        Video save = videoRepository.save(video);
        if (save != null){
            System.out.println(save);
            rabbitTemplate.setConfirmCallback(this);
            rabbitTemplate.convertAndSend("topicExchange.video","topic.videoSave",save);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean deleteVideoById(Integer id) {
        if (videoRepository.deleteByVideoId(id) > 0){
            rabbitTemplate.setConfirmCallback(this);
            rabbitTemplate.convertAndSend("topicExchange.video","topic.videoDelete",id);
            return true;
        }
        return false;
    }

    @Override
    public Video findVideoById(Integer id) {
        Optional<Video> optionalVideo = videoRepository.findById(id);
        Video video = optionalVideo.get();
        return video;
    }

    @Override
    public Boolean updateVideo(Video video) {
        if(video != null){
            Video video1 = videoRepository.saveAndFlush(video);
            if (video1 != null){
                rabbitTemplate.setConfirmCallback(this);
                rabbitTemplate.convertAndSend("topicExchange.video","topic.videoUpdate",video1);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public VideoResponse findVideo(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Video> all = videoRepository.findAll(pageable);
        VideoResponse videoResponse = new VideoResponse();
        videoResponse.setVideoList(all.getContent());
        videoResponse.setVideoTotal(all.getTotalElements());
        return videoResponse;
    }

    @Override
    public List<Video> selectVideoToEs() {
        List<Video> videoList = videoRepository.findAll();
        if (videoList !=null){
            return videoList;
        }
        return null;
    }

    @Override
    public String getVideoPicPath(MultipartFile file) {
        if (!Objects.isNull(file) && !file.isEmpty()){
//            String jsonString = JSON.toJSONString(file, true);
            String path = uploadUtils.getPath(file);
            return path;
        }
        return null;
    }

    @Override
    public VideoResponse findVideoByShowTime(Integer page, Integer size) {
        Pageable pages=PageRequest.of(page-1,size);

        List<Video> videoList = null;
        VideoResponse<Video> r=new VideoResponse();
        Page<Video> all=videoRepository.findAll(pages);
        //Sort sort = new Sort(Sort.Direction.ASC, "videoShowTime");
        //videoList = videoRepository.findAll(sort);
        //PageRequest pageable = new PageRequest(page - 1, size,sort);
        //System.out.println(pageable+"////");
        //Page<Video> all = videoRepository.findAll(pageable);

        Integer index = (page - 1) * size;
        videoList = videoRepository.findAllByShowTimeByPage(index, size);

        //System.out.println(all.getTotalElements()+"333");
        //videoList = all.getContent();
        r.setVideoTotal(all.getTotalElements());
        // System.out.println(all.getTotalElements());
        Set<String> videoIndex = redisUtils.ZRevRange(page+"videoIndex1", 0, -1);
        if (videoIndex !=null && !"noData".equals(videoIndex) && !videoIndex.isEmpty()){
            String jsonString = JSON.toJSONString(videoIndex);
            videoList = JSONObject.parseArray(jsonString, Video.class);
            r.setVideoList(videoList);

            return r;
        }
        r.setVideoList(videoList);
        if (videoList != null){
            for (Video video : videoList) {
                long showTime = video.getVideoShowTime().getTime();
                redisTemplate.opsForZSet().add(page+"videoIndex1",video,showTime);
            }
        }else {
            redisUtils.ZSet(page+"videoIndex1","noData",1);
        }
        return r;
    }

    @Override
    public VideoResponse findVideoByGander(Integer page, Integer size) {
        Pageable pages=PageRequest.of(page-1,size);
        List<Video> videoList = null;
        VideoResponse<Video> r=new VideoResponse();
        Page<Video> all=videoRepository.findAll(pages);
        Integer index = (page - 1) * size;
        videoList = videoRepository.findAllByGradeByPage(index,size);
        r.setVideoTotal(all.getTotalElements());
        Set<String> videoIndex = redisUtils.ZRevRange(page+"videoIndex2", 0, -1);
        if (videoIndex !=null && !"noData".equals(videoIndex) && !videoIndex.isEmpty()){
            String jsonString = JSON.toJSONString(videoIndex);
            videoList = JSONObject.parseArray(jsonString, Video.class);
            r.setVideoList(videoList);

            return r;
        }
//        Sort sort = new Sort(Sort.Direction.ASC, "videoGrade");
//        videoList = videoRepository.findAll(sort);
        r.setVideoList(videoList);
        if (videoList != null){
            for (Video video : videoList) {
                redisTemplate.opsForZSet().add(page+"videoIndex2",video,video.getVideoGrade());
            }
        }else {
            redisUtils.ZSet(page+"videoIndex2","noData",1);
        }
        return r;
    }

    @Override
    public String getVideoPath(MultipartFile file) {
        String videoPath = null;
        if (file != null){
            System.out.println(file.getName());
            logger.info("============>文件上传" + file.getName() + "------------>" + file.getOriginalFilename());
//            String path = "D:\\project\\part4\\part4-project\\resource\\pic\\004.jpg";
            videoPath = videoUploadUtils.videoUpload(file);
            System.out.println(videoPath);
        }
        return videoPath;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("uuid" + correlationData.getId() + "ack" + b + "cause" + s);
    }
}
