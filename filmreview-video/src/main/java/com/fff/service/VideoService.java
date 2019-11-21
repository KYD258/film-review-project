package com.fff.service;

import com.fff.Responses.VideoResponse;
import com.fff.domain.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    Boolean saveVideo(Video video);

    Boolean deleteVideoById(Integer id);

    Boolean updateVideo(Video video);

    Video findVideoById(Integer id);
    //    查询所有
    VideoResponse findVideo(Integer page, Integer size);
//    导es查询
    List<Video> selectVideoToEs();
//    图片地址
    //    图片地址
    String getVideoPicPath(MultipartFile file);
    //    按是否在首页展示搜索并按时间排序
    VideoResponse findVideoByShowTime(Integer page, Integer size);
    //    按是否在首页展示搜索并按评分排序
    VideoResponse findVideoByGander(Integer page, Integer size);
    //    视频地址
    String getVideoPath(MultipartFile file);
}
