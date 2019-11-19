package com.fff.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 周边商品实体类
 */
@Data
public class Commodity {
    private Integer commodityId;
    private String commodityName;
    private String commodityPic;
    private String description;
    private Double commodityPrice;
    private Integer commodityNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date addTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date outTime;
    private String commodityInfo1;
}
