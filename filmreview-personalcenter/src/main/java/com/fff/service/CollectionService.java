package com.fff.service;

import com.fff.domain.Collection;
import com.fff.domain.Video;

import java.util.List;

public interface CollectionService {
    /*收藏与订阅*/
    void saveCollection(Collection collection);
    /*取消收藏或订阅*/
    void deleteCollection(Collection collection);
    /*查询收藏或订阅*/
    List<Video> findAllCollection(Integer userId);
    /*查询收藏1*/
    List<Video> findCollection(Integer userId);
    /*查询订阅2*/
    List<Video> findSubscription(Integer userId);
}
