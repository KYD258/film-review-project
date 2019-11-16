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
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
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
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
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
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
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
    public List<Video> findVideo() {
        List<Video> videoList = videoRepository.findAll();
        return videoList;
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
    public List<Video> findVideoByShowTime() {
        List<Video> videoList = null;
        Set<String> videoIndex = redisUtils.ZRevRange("videoIndex1", 0, -1);
        if (videoIndex !=null && !"noData".equals(videoIndex)){
            String jsonString = JSON.toJSONString(videoIndex);
            videoList = JSONObject.parseArray(jsonString, Video.class);
            return videoList;
        }
        Sort sort = new Sort(Sort.Direction.ASC, "videoShowTime");
        videoList = videoRepository.findAll(sort);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (videoList != null){
            for (Video video : videoList) {
                Long showTime = Long.parseLong(format.format(video.getVideoShowTime()));
                redisTemplate.opsForZSet().add("videoIndex1",video,showTime);
            }
        }else {
            redisUtils.ZSet("videoIndex1","noData",1);
        }
        return videoList;
    }

    @Override
    public List<Video> findVideoByGander() {
        List<Video> videoList = null;
        Set<String> videoIndex = redisUtils.ZRevRange("videoIndex2", 0, -1);
        if (videoIndex !=null && !"noData".equals(videoIndex)){
            String jsonString = JSON.toJSONString(videoIndex);
            videoList = JSONObject.parseArray(jsonString, Video.class);
            System.out.println(videoIndex);
            return videoList;
        }
        Sort sort = new Sort(Sort.Direction.ASC, "videoGrade");
        videoList = videoRepository.findAll(sort);
        if (videoList != null){
            for (Video video : videoList) {
                redisTemplate.opsForZSet().add("videoIndex2",video,video.getVideoGrade());
            }
        }else {
            redisUtils.ZSet("videoIndex2","noData",1);
        }
        return videoList;
    }

    @Override
    public String getVideoPath(MultipartFile file) {
        String videoPath = null;
        if (file != null){
            System.out.println(file.getName());
            logger.info("============>文件上传" + file.getName() + "------------>" + file.getOriginalFilename());
//            String path = "D:\\project\\part4\\part4-project\\resource\\pic\\004.jpg";
            videoPath = videoUploadUtils.videoUpload(file);
            String substring = videoPath.substring(videoPath.lastIndexOf("."));
            if (".m3u8".equals(substring)){
                return videoPath;
            }
        }
        return "success";
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        logger.info("uuid" + correlationData.getId() + "ack" + b + "cause" + s);
    }
}
