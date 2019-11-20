package com.fff.commons;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Data
public class GetReview{
    private Integer reviewId;
    private String reviewContent;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date reviewCreateTime;
    private String userName;
    private String userPic;
    private Float reviewGrade;
    private Integer videoId;
}
