package com.fff.service;

import com.fff.domain.Creation;

public interface CreationService {
    /*用户个人作品上传*/
    void addUserCreation(Creation creation);
    /*用户个人作品删除*/
    void deleteUserCreation(Integer creationId);
    /*用户根据creation_id查询评论*/
    Creation getCreationMessage(Integer creationId);
    /*用户个人作品修改*/
    void updateUserCreation(Creation creation);
}
