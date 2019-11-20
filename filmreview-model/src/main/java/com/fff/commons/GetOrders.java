package com.fff.commons;

import lombok.Data;

import java.util.Date;

@Data
public class GetOrders {
    private Integer commodityId;
    private String commodityName;
    private String commodityPic;
    private String description;
    private Double commodityPrice;
    private Integer commodityNum;
    private Date addTime;
    private Date outTime;
    private Integer orderStatus;
    private Integer userId;
    private Integer orderNum;
    private Integer id;
}
