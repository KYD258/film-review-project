package com.fff.commons;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class GetReview {
    private Integer reviewId;
    private String reviewContent;
    private Date reviewCreateTime;
    private String userName;
}
