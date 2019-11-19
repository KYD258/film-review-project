package com.fff.dao;

import com.fff.commons.GetReview;
import com.fff.domain.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    public List<Review> findByUserId(Integer userId);
    public List<GetReview> findByVideoId(Integer videoId);
    public Integer findCount(@Param("videoId") Integer videoId,@Param("userId") Integer userId);
    public Review findReview(@Param("videoId") Integer videoId,@Param("userId") Integer userId);
}
