package com.fff.commons;

import lombok.Data;

import java.util.Date;

@Data
public class GetOrders {
    private Integer commodity_id;
    private String commodity_name;
    private String commodity_pic;
    private String description;
    private Double commodity_price;
    private Integer commodity_num;
    private Date addTime;
    private Date outTime;
    private String commodity_info1;
    private Integer order_status;
    private Integer user_id;
}
