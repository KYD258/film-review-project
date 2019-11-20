package com.fff.service.impl;

import com.fff.commons.GetAllCreation;
import com.fff.dao.CreationMapper;
import com.fff.dao.CreationRepository;
import com.fff.dao.UserCreationRepository;
import com.fff.domain.Creation;
import com.fff.domain.UserCreation;
import com.fff.service.CreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreationServiceImpl implements CreationService {
    @Autowired
    private CreationRepository creationRepository;
@Autowired
private CreationMapper creationMapper;
@Autowired
private UserCreationRepository userCreationRepository;
    @Override
    public void deleteUserCreation(Integer creationId) {
        creationRepository.deleteById(creationId);
    }


    @Override
    public List<GetAllCreation> getCreationAllMessage() {
        List<GetAllCreation> all = creationMapper.getCreationAllMessage();
        return all;
    }

    @Override
    public void updateUserCreation(Creation creation) {
      creationRepository.saveAndFlush(creation);

    }

    @Override
    public Creation getCreationMessage(Integer creationId) {
        Creation creation = creationRepository.findById(creationId).get();
        return creation;
    }

    @Override
    public void save(UserCreation userCreation) {
        userCreationRepository.save(userCreation);
    }

    @Override
    public List<GetAllCreation> findUserCreation(Integer userId) {
        List<GetAllCreation> userCreation = creationMapper.findUserCreation(userId);
        return userCreation;
    }

    @Override
    public Creation addUserCreation(Creation creation) {
        Creation save = creationRepository.save(creation);
        return save;
    }
}
