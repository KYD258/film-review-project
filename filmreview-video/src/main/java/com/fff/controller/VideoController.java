package com.fff.controller;

import com.fff.Responses.VideoResponse;
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

    @RequestMapping("/findVideo/{page}/{size}")
    public VideoResponse findVideo(@PathVariable("page")Integer page,@PathVariable("size")Integer size){
        VideoResponse videoResponse = videoService.findVideo(page, size);
        return videoResponse;
    }

    @RequestMapping("/selectVideoToEs")
    public List<Video> selectVideoToEs(){
        return videoService.selectVideoToEs();
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

    @RequestMapping("/getDataByShowTime/{page}/{size}")
    public VideoResponse getDataByShowTime(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return videoService.findVideoByShowTime(page,size);
    }

    @RequestMapping("/getDataByVideoGander/{page}/{size}")
    public VideoResponse getDataByVideoGander(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return videoService.findVideoByGander(page,size);
    }

//    获取视频地址

    @RequestMapping("/getVideoPath")
    public String getVideoPath(MultipartFile file){
        return videoService.getVideoPath(file);
    }
}
