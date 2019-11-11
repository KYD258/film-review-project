package com.fff.service;

import com.fff.domain.UserOrders;
import com.fff.responses.GetOrders;

import java.util.List;

public interface OrdersService {
    /*订单的删除
    *
    *
    * */
    void deleteOrderByOrderId(Integer id);

    /*订单的添加*/
    public UserOrders addOrder(UserOrders userOrders) ;

    /*全部订单查询*/
    List<GetOrders> findAllOrder(Integer userId);

    List<UserOrders> findAll(Integer userId);

    List<GetOrders> payOrder(Integer userId);

    List<GetOrders> noPayOrder(Integer userId);
}
