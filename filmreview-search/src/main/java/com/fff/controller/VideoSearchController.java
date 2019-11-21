package com.fff.controller;

import com.fff.Responses.VideoResponse;
import com.fff.commons.R;
import com.fff.domain.Video;
import com.fff.service.VideoSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/videoSearch")
public class VideoSearchController {
    @Autowired
    private VideoSearchService videoSearchService;

    @RequestMapping("/createIndex")
    public R createIndex(){
        if (videoSearchService.createIndex()){
            return R.ok();
        }
        return R.error();
    }

    @RequestMapping("/toES")
    public void toEs(){
       videoSearchService.toEs();
    }

    @RequestMapping("/saveVideoToEs")
    public void saveVideoToEs(@RequestBody Video video){
        videoSearchService.saveVideoToEs(video);
    }

    @RequestMapping("/deleteVideoFromEs")
    public void deleteVideoFromEs(@RequestParam("id") Integer id){
        videoSearchService.deleteVideoFromEs(id);
    }

    @RequestMapping("/updateVideoInEs")
    public void updateVideoInEs(@RequestBody Video video){
        videoSearchService.updateVideoInEs(video);
    }

    @RequestMapping(value = "/selectVideoByPage",method = RequestMethod.POST)
    public VideoResponse selectVideoByPage(@RequestParam("page")Integer page,@RequestParam("size")Integer size,@RequestParam("keys")String keys){
        System.out.println(keys);
        VideoResponse videoResponse = videoSearchService.selectVideoByPage(page,size,keys);
        return videoResponse;
    }
    @RequestMapping(value = "/findVideoByClassify",method = RequestMethod.POST)
    public VideoResponse findVideoByClassify(@RequestParam("page")Integer page,@RequestParam("size")Integer size,@RequestParam("keys")String keys){
        System.out.println(keys);
        VideoResponse videoResponse = videoSearchService.findVideoByClassify(page, size, keys);
        return videoResponse;
    }
    @RequestMapping(value = "/findVideoByType",method = RequestMethod.POST)
    public VideoResponse findVideoByType(@RequestParam("page")Integer page,@RequestParam("size")Integer size,@RequestParam("classifyKey")String classifyKey, @RequestParam("typeKey")String typeKey){
        System.out.println(classifyKey + "......." + typeKey);
        VideoResponse videoResponse = videoSearchService.findVideoByType(page,size,classifyKey,typeKey);
        return videoResponse;
    }
}
