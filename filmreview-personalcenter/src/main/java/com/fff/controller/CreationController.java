package com.fff.controller;

import com.alibaba.fastjson.JSON;
import com.fff.commons.GetAllCreation;
import com.fff.commons.R;
import com.fff.domain.Creation;
import com.fff.domain.UserCreation;
import com.fff.service.CreationService;
import com.fff.utils.UploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/*用户个人作品上传 /是删除*/
@RestController
@RequestMapping("/creation")
public class CreationController {
    @Autowired
    private CreationService creationService;
    @Autowired
    private UploadUtils uploadUtils;
    private Logger logger = LoggerFactory.getLogger(CreationController.class);
    /*用户个人作品上传*/
    @RequestMapping(value = "/addUserCreation",method = RequestMethod.POST)
    public R addUserCreation(@RequestBody Creation creation,HttpSession session){
        System.out.println(creation+"++++");
        Integer userId = (Integer) session.getAttribute("userId");
        userId=1;
        Creation creation1 = creationService.addUserCreation(creation);
        UserCreation userCreation = new UserCreation();
        userCreation.setCreationId(creation1.getCreationId());
        userCreation.setUserId(userId);
        creationService.save(userCreation);
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
            System.out.println(path+"---------------");
            return path;
        }
        return null;
    }


    /*用户个人作品删除*/
    @RequestMapping("/deleteUserCreation")
    public R deleteUserCreation(@RequestBody Creation creation){
        System.out.println(creation+"+++");
        creationService.deleteUserCreation(creation.getCreationId());
        return R.ok();
    }
    /*用户个人所有作品查询*/
    @RequestMapping("/findUserCreation")
    public List<GetAllCreation> findUserCreation(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        userId=1;
        List<GetAllCreation> userCreation = creationService.findUserCreation(userId);
        return userCreation;
    }
    /*用户根据creation_id查询*/
    @RequestMapping("/getCreationMessage")
    public Creation getCreationMessage(@RequestBody Creation creation){
        Creation creationMessage = creationService.getCreationMessage(creation.getCreationId());
        return creationMessage;
    }
    /*用户个人作品修改*/
    @RequestMapping("/updateUserCreation")
    public R updateUserCreation(@RequestBody Creation creation){
        creationService.updateUserCreation(creation);
            return R.ok();
    }
    /*查询所有动态*/
    @RequestMapping("/getCreationAllMessage")
    public List<GetAllCreation> getCreationAllMessage(){
        List<GetAllCreation> creationAllMessage = creationService.getCreationAllMessage();
        return creationAllMessage;
    }


}
