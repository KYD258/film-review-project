package com.fff.service;

import com.fff.domain.UserOrders;
import com.fff.commons.GetOrders;

import java.util.List;

public interface OrdersService {
    /*订单的删除
    *
    *
    * */
    public void deleteOrderByOrderId(Integer id);

    /*订单的添加*/
    public void addOrder(UserOrders userOrders) ;

    /*全部订单查询*/
    List<GetOrders> findAllOrder(Integer userId);

    List<UserOrders> findAll(Integer userId);

    List<GetOrders> payOrder(Integer userId);

    List<GetOrders> noPayOrder(Integer userId);

    /*订单的修改*/
    public void updateStatus(String orderNum);

    public UserOrders findByOrderNum(String orderNum);

    Double findPriceByUserId(Integer userId);
}
