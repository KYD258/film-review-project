package com.fff.service;

import com.fff.Responses.VideoResponse;
import com.fff.domain.Video;

import java.io.IOException;

public interface VideoSearchService {
    //  添加索引
    Boolean createIndex();
    //  导入数据
    Boolean toEs();
    //  增加视频
    void saveVideoToEs(Video video);
    //  删除视频
    void deleteVideoFromEs(Integer id);
    //  修改视频
    void updateVideoInEs(Video video);
    //  es分页搜索视频
    VideoResponse selectVideoByPage(Integer page, Integer size, String keys);
}
