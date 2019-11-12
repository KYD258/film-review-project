package com.fff.service.impl;

import com.fff.dao.UserCreationMapper;
import com.fff.domain.Creation;
import com.fff.service.UserCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserCreationServiceImpl implements UserCreationService {
    @Autowired
    private UserCreationMapper userCreationMapper;
    @Override
    public List<Creation> findAllUserCreation(Integer userId) {
        List<Creation> allUserCreation = userCreationMapper.findAllUserCreation(userId);
        return allUserCreation;
    }
}
