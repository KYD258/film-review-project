package com.fff.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_orders")
public class UserOrders {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "order_num")
    private String orderNum;
    @Column(name = "commodity_id")
    private Integer commodityId;
    @Column(name = "order_status")
    private Integer orderStatus;
}
