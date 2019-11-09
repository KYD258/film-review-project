package com.fff.domain;

import lombok.Data;

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
    private Integer commodityNum;
    private Date addTime;
    private Date outTime;
    private String commodityInfo1;
}
