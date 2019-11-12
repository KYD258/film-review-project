package com.fff.service;

import com.fff.domain.Creation;

import java.util.List;

public interface UserCreationService {
    /*用户个人所有作品查询*/
    List<Creation> findAllUserCreation(Integer userId);
}
