package com.fff.dao;

import com.fff.domain.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<Review> findByUserId(Integer userId);
}
