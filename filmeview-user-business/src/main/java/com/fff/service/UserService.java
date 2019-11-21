package com.fff.service;

import com.fff.domain.Code;
import com.fff.domain.User;
import com.fff.response.UserAndCode;

import javax.servlet.http.HttpSession;

public interface UserService {


    String register(UserAndCode user);

    String sendCode(String phone);

    void regByPhone(Code code);

    String loginByPhone(Code code);

    String  loginByPassword(User user);

    String toVip(User user);

    Boolean havePms(User user);

    Boolean isAdmin(User user);

    Boolean addUser(User user);

    Boolean deleteUser(User user);

    Boolean updateUser(User user);


}
