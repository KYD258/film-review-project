package com.fff.dao;

import com.fff.domain.UserOrders;
import com.fff.responses.GetOrders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserOrdersMapper {
    /*查询所有订单*/
    List<GetOrders> findAllOrder(Integer userId);
    /*查询已支付订单*/
    List<GetOrders> payOrder(Integer userId);
    /*查询wei支付订单*/
    List<GetOrders> noPayOrder(Integer userId);
}
