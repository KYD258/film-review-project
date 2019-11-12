package com.fff.service.impl;

import com.fff.dao.CreationRepository;
import com.fff.domain.Creation;
import com.fff.service.CreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreationServiceImpl implements CreationService {
    @Autowired
    private CreationRepository creationRepository;

    @Override
    public void deleteUserCreation(Integer creationId) {
        creationRepository.deleteById(creationId);
    }

    @Override
    public void addUserCreation(Creation creation) {
        creationRepository.save(creation);
    }
}
