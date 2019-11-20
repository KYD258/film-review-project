package com.fff.controller;

import com.alibaba.fastjson.JSON;
import com.fff.commons.R;
import com.fff.domain.User;
import com.fff.service.UserService;
import com.fff.utils.UploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/*
* 用户个人中心 / 修改个人信息
*
*/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UploadUtils uploadUtils;
    private Logger logger = LoggerFactory.getLogger(CreationController.class);
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
     * 通过用户id修改用户信息
     * */
    @RequestMapping("/updateUserMessage")
    public R updateUserMessage(@RequestBody User user){
        userService.updateUserMessage(user);
        return R.ok();
    }
    /*用户图片上传*/
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
}
