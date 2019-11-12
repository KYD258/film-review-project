package com.fff.service;

import com.fff.domain.Collection;

import java.util.List;

public interface CollectionService {
    /*收藏与订阅*/
    void saveCollection(Collection collection);
    /*取消收藏或订阅*/
    void deleteCollection(Integer collectionId);
    /*查询收藏或订阅*/
    List<Collection> findAllCollection();
}
