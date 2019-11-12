package com.fff.service.impl;

import com.fff.dao.CollectionRepository;
import com.fff.domain.Collection;
import com.fff.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;

    @Override
    public List<Collection> findAllCollection() {
        List<Collection> all = collectionRepository.findAll();
        return all;
    }

    @Override
    public void deleteCollection(Integer collectionId) {
        collectionRepository.deleteById(collectionId);
    }

    @Override
    public void saveCollection(Collection collection) {
        collectionRepository.save(collection);
    }
}
