package com.fff.dao;

import com.fff.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void toVip(Integer userId);

    Integer havePms(Integer userId);
    Integer isAdmin(Integer userId);

    Integer updateUser(User user);


}
