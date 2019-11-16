package com.fff.controller;

import com.alibaba.fastjson.JSON;
import com.fff.commons.R;
import com.fff.domain.Creation;
import com.fff.service.CreationService;
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
    @RequestMapping("/addUserCreation")
    public R addUserCreation(@RequestBody Creation creation){
        creationService.addUserCreation(creation);
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


    /*用户个人作品删除*/
    @RequestMapping("/deleteUserCreation")
    public R deleteUserCreation(@RequestBody Creation creation){
        creationService.deleteUserCreation(creation.getCreationId());
        return R.ok();
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
}
