package com.fff.service;

import com.fff.domain.Creation;

public interface CreationService {
    /*用户个人作品上传*/
    void addUserCreation(Creation creation);
    /*用户个人作品删除*/
    void deleteUserCreation(Integer creationId);
}
