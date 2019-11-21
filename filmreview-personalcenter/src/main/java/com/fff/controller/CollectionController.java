package com.fff.controller;

import com.fff.commons.R;
import com.fff.domain.Collection;
import com.fff.domain.Video;
import com.fff.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/*收藏与订阅/取消收藏或订阅/查询收藏或订阅*/
@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;
    /*添加收藏*/
    @RequestMapping("/saveCollection")
    public R saveCollection(@RequestBody Video video, HttpSession session){
        Integer videoId = video.getVideoId();
        Integer userId = (Integer)session.getAttribute("userId");
        Collection collection = new Collection();
        collection.setUserId(userId);
        collection.setVideoId(videoId);
        video.setCollectionOrsubscription(1);
        collectionService.saveCollection(collection);
        return R.ok();
    }
    /*添加 订阅*/
    @RequestMapping("/saveSubscription")
    public R saveSubscription(@RequestBody Video video, HttpSession session){
        Integer videoId = video.getVideoId();
        Integer userId = (Integer)session.getAttribute("userId");
        Collection collection = new Collection();
        collection.setUserId(userId);
        collection.setVideoId(videoId);
        video.setCollectionOrsubscription(2);
        collectionService.saveCollection(collection);
        return R.ok();
    }
    /*取消收藏或订阅*/
    @RequestMapping("/deleteCollection")
    public R deleteCollection(@RequestBody Video video, HttpSession session){
        Integer videoId = video.getVideoId();
        Integer userId = (Integer)session.getAttribute("userId");
        Collection collection = new Collection();
        collection.setUserId(1);
        collection.setVideoId(videoId);
        collectionService.deleteCollection(collection);
        return R.ok();
    }
    /*查询全部收藏或订阅*/
    @RequestMapping("/findAllCollection")
    public List<Video> findAllCollection(HttpSession session){
        Integer userId = (Integer)session.getAttribute("userId");
        userId=1;
        List<Video> videos = collectionService.findAllCollection(userId);
        return videos;
    }
    /*查询收藏1*/
    @RequestMapping("/findCollection")
    public List<Video> findCollection(HttpSession session){
        Integer userId = (Integer)session.getAttribute("userId");
        userId=1;
        List<Video> videos = collectionService.findCollection(userId);
        System.out.println(videos);
        return videos;
    }

    /*查询订阅2*/
    @RequestMapping("/findSubscription")
    public List<Video> findSubscription(HttpSession session){
        Integer userId = (Integer)session.getAttribute("userId");
        userId=1;
        List<Video> videos = collectionService.findSubscription(userId);
        System.out.println(videos);
        return videos;
    }
}
