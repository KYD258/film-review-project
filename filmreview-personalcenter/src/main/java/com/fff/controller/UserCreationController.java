package com.fff.controller;

import com.fff.domain.Creation;
import com.fff.service.UserCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/*用户个人作品查询*/
@RestController
@RequestMapping("/UserCreation")
public class UserCreationController {
    @Autowired
    private UserCreationService userCreationService;
    /*用户个人所有作品查询*/
@RequestMapping("/findAllUserCreation")
    public List<Creation> findAllUserCreation(HttpSession session){
    Integer userId = (Integer) session.getAttribute("userId");
    List<Creation> allUserCreation = userCreationService.findAllUserCreation(userId);
    return allUserCreation;
}
}
