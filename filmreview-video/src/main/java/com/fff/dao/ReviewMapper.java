package com.fff.dao;

import com.fff.commons.GetReview;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    public Float countGrade(Integer videoId);
}
