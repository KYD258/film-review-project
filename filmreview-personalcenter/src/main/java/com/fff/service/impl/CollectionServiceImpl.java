package com.fff.service.impl;

import com.fff.dao.CollectionMapper;
import com.fff.dao.CollectionRepository;
import com.fff.domain.Collection;
import com.fff.domain.Video;
import com.fff.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public List<Video> findSubscription(Integer userId) {
        List<Video> videos = collectionMapper.findSubscription(userId);
        return videos;
    }

    @Override
    public List<Video> findCollection(Integer userId) {
        List<Video> videos = collectionMapper.findCollection(userId);
        return videos;
    }

    @Override
    public List<Video> findAllCollection(Integer userId) {
        List<Video> all = collectionMapper.findAllCollection(userId);
        return all;
    }

    @Override
    public void deleteCollection(Collection collection) {

        //collectionRepository.deleteById(collectionId);
        //collectionRepository.delete(collection);
        collectionMapper.deleteCollection(collection);
    }

    @Override
    public void saveCollection(Collection collection) {
        collectionRepository.save(collection);
    }
}
