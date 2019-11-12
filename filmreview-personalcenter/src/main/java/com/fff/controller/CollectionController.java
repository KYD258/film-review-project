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
    /*收藏与订阅*/
    @RequestMapping("/saveCollection")
    public R saveCollection(@RequestBody Video video, HttpSession session){
        Integer videoId = video.getVideoId();
        Integer userId = (Integer)session.getAttribute("userId");
        Collection collection = new Collection();
        collection.setUserId(userId);
        collection.setVideoId(videoId);
        collectionService.saveCollection(collection);
        return R.ok();
    }
    /*取消收藏或订阅*/
    @RequestMapping("/deleteCollection")
    public R deleteCollection(@RequestBody Collection collection){
        collectionService.deleteCollection(collection.getCollectionId());
        return R.ok();
    }
    /*查询收藏或订阅*/
    @RequestMapping("/findAllCollection")
    public List<Collection> findAllCollection(){
        List<Collection> allCollection = collectionService.findAllCollection();
        return allCollection;
    }
}
