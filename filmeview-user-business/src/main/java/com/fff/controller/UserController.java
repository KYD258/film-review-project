package com.fff.controller;


import com.alibaba.fastjson.JSON;
import com.fff.domain.Code;
import com.fff.domain.User;
import com.fff.response.UserAndCode;
import com.fff.service.UserService;
import com.fff.utils.UploadUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@RestController
public class UserController {
    Logger logger=LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UploadUtils uploadUtils;
    @Autowired
    private RedisTemplate redisTemplate;

    //注册
    @RequestMapping("/userReg")
    public String reg(@RequestBody UserAndCode user){
        System.out.println(user+"-----");
        String register = userService.register(user);
        return register;
    }

    //短信发送
    @RequestMapping("/sendCode/{phone}")
    public String  getCode(@PathVariable("phone")String phone){
        String code = userService.sendCode(phone);
        return code;
    }
    //登录考虑session共享，以及多用户登录问题,单点登录
    @RequestMapping("/loginByPhone")
    public String loginByPhone(@RequestBody Code code,
                               HttpServletRequest req,
                               HttpServletResponse resp,
                               HttpSession session){
        String s = userService.loginByPhone(code);
        if (s.equals("登陆成功！")){
        }
        return s;
    }

    @RequestMapping("/loginByPassword")
    public String loginByPassword(@RequestBody User user,
                                  HttpServletRequest req,
                                  HttpServletResponse resp,
                                  HttpSession session){
        String s = userService.loginByPassword(user);
        if (s.equals("success")){

        }
        return s;
    }

    @RequestMapping("/loginOut")
    public void loginOut(HttpServletRequest req, HttpServletResponse resp){
         Subject subject=SecurityUtils.getSubject();
         subject.logout();
    }

    @RequestMapping("/getPath")
    public String getPath(MultipartFile file){
        logger.debug("传入的文件参数:{}",JSON.toJSONString(file,true));
        if(Objects.isNull(file) || file.isEmpty()){
            logger.error("文件为空");
        }else {
            String path = uploadUtils.getPath(file);
            return path;
        }
        return null;
    }

    @RequestMapping("UserToVip")
    public String  toVip(@RequestBody User user){
        String s = userService.toVip(user);
        return s;
    }
    @RequestMapping("/isVip")
    public Boolean havePms(@RequestBody User user){
        Boolean aBoolean = userService.havePms(user);
        return aBoolean;
    }
    @RequestMapping("/isAdmin")
    public Boolean isAdmin(@RequestBody User user){
        Boolean admin = userService.isAdmin(user);
        return admin;
    }

    @RequestMapping("/addUser")
    public Boolean addUser(@RequestBody User user){
        Boolean aBoolean = userService.addUser(user);
        return aBoolean;
    }

    @RequestMapping("/deleteUser")
    public Boolean deleteUser(@RequestBody User user){
        Boolean aBoolean = userService.deleteUser(user);
        return aBoolean;
    }

    @RequestMapping("/updateUser")
    public Boolean updateUser(@RequestBody User user){
        Boolean aBoolean = userService.updateUser(user);
        return  aBoolean;
    }


}
