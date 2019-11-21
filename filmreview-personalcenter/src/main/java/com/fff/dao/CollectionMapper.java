package com.fff.dao;

import com.fff.domain.Collection;
import com.fff.domain.Video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectionMapper {
    /*查询收藏1*/
    List<Video> findCollection(Integer userId);
    /*查询订阅2*/
    List<Video> findSubscription(Integer userId);
    /*查询收藏或订阅*/
    List<Video> findAllCollection(Integer userId);

    void deleteCollection(Collection collection);
}
