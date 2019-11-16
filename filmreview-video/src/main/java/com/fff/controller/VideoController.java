package com.fff.controller;

import com.fff.commons.R;
import com.fff.domain.Video;
import com.fff.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping("/findVideo")
    public List<Video> findVideo(){
        List<Video> videoList = videoService.findVideo();
        return videoList;
    }

    @RequestMapping("/saveVideo")
    public R saveVideo(@RequestBody Video video){
        if (videoService.saveVideo(video)){
            return R.ok();
        }
        return R.error();
    }

    @RequestMapping("/deleteVideo")
    public R deleteVideo(@RequestParam("id")Integer id){
        if (videoService.deleteVideoById(id)){
            return R.ok();
        }
        return R.error();
    }

    @RequestMapping("/findVideoById/{id}")
    public Video findVideoById(@PathVariable("id") Integer id){
        return videoService.findVideoById(id);
    }

    @RequestMapping("/updateVideo")
    public R updateVideo(@RequestBody Video video){
        if (videoService.updateVideo(video)){
            return R.ok();
        }
        return R.error();
    }
//    获取视频图片地址
    @RequestMapping("/getVideoPicPath")
    public String getVideoPicPath(MultipartFile file){
        String picPath = videoService.getVideoPicPath(file);
        return picPath;
    }

    @RequestMapping("/getDataByShowTime")
    public List<Video> getDataByShowTime(){
        return videoService.findVideoByShowTime();
    }

    @RequestMapping("/getDataByVideoGander")
    public List<Video> getDataByVideoGander(){
        return videoService.findVideoByGander();
    }

//    获取视频地址

    @RequestMapping("/getVideoPath")
    public String getVideoPath(MultipartFile file){
        return videoService.getVideoPath(file);
    }
}
