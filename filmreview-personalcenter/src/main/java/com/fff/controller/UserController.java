package com.fff.controller;

import com.fff.domain.User;
import com.fff.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/*
* 用户个人中心 / 修改个人信息
*
*/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*
    * 获取用户个人信息,通过用户id
    * */
    @RequestMapping("/getUserMessage")
    public User getUserMessage(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        User user = userService.findById(userId);
        return user;
    }
    /*
     * 通过用户id修改用户信息返回1表示成功
     * */
    @RequestMapping("/updateUserMessage")
    public Integer updateUserMessage(@RequestBody User user){
        userService.updateUserMessage(user);
        return 1;
    }

}
