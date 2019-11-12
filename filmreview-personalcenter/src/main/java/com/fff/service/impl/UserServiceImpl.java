package com.fff.service.impl;

import com.fff.dao.UserRepository;
import com.fff.domain.User;
import com.fff.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void updateUserMessage(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User findById(Integer userId) {
        return userRepository.findById(userId).get();
    }
}
