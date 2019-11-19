package com.fff.service;

import com.fff.commons.GetAllCreation;
import com.fff.domain.Creation;
import com.fff.domain.UserCreation;

import java.util.List;

public interface CreationService {
    /*用户个人作品上传*/
    Creation addUserCreation(Creation creation);
    /*用户个人作品删除*/
    void deleteUserCreation(Integer creationId);
    /*用户根据creation_id查询评论*/
    Creation getCreationMessage(Integer creationId);
    /*用户个人作品修改*/
    void updateUserCreation(Creation creation);
    /*查询所有动态*/
    List<GetAllCreation> getCreationAllMessage();

    void save(UserCreation userCreation);

    List<GetAllCreation> findUserCreation(Integer userId);
}
