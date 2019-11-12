package com.fff.dao;

import com.fff.domain.Creation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserCreationMapper {
    /*用户个人所有作品查询*/
    List<Creation> findAllUserCreation(Integer userId);
}
