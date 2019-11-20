package com.fff.dao;

import com.fff.commons.GetAllCreation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreationMapper {
    /*查询所有动态*/
    List<GetAllCreation> getCreationAllMessage();
    /*查询用户所有动态*/
    List<GetAllCreation> findUserCreation(Integer userId);
}
