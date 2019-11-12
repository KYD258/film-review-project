package com.fff.controller;

import com.fff.domain.Creation;
import com.fff.service.CreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/*用户个人作品上传 /是删除*/
@RestController
@RequestMapping("/creation")
public class CreationController {
    @Autowired
    private CreationService creationService;

    /*用户个人作品上传*/
    @RequestMapping("/addUserCreation")
    public Integer addUserCreation(@RequestBody Creation creation){
        creationService.addUserCreation(creation);
        return 1;
    }
    /*用户个人作品删除*/
    @RequestMapping("/deleteUserCreation")
    public Integer deleteUserCreation(@RequestBody Creation creation){
        creationService.deleteUserCreation(creation.getCreationId());
        return 1;
    }
}
