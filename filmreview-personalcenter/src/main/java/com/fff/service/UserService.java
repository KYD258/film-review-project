package com.fff.service;

import com.fff.domain.User;

public interface UserService {
    /*通过用户Id查询用户信息*/
    User findById(Integer userId);
    /*修改用户信息*/
    void updateUserMessage(User user);
}
