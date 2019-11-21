package com.fff.dao;

import com.fff.ErrorFallBack.VideoFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "filmreview-video", fallback = VideoFallBack.class)
public interface VideoInterface {
    @GetMapping("/video/selectVideoToEs")
    String getVideoData();
}
