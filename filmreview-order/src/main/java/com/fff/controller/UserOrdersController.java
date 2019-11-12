package com.fff.controller;

import com.fff.commons.R;
import com.fff.domain.UserOrders;
import com.fff.responses.GetOrders;
import com.fff.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.log.LogInputStream;

import javax.servlet.http.HttpSession;
import java.util.List;

/*
 * 订单的查询 / 添加  / 删除
 *
 * */
@RestController
@RequestMapping("/order")
public class UserOrdersController {
    @Autowired
    private OrdersService ordersService;

    /*支付成功订单的添加状态1表示已支付0未支付*/
    @RequestMapping("/addOrder")
    public UserOrders addOrder(@RequestBody UserOrders userOrders) {
        UserOrders userOrders1 = ordersService.addOrder(userOrders);
        return userOrders1;
    }

    /*订单的删除*/
    @RequestMapping("/deleteOrderByOrderId")
    public R deleteOrderByOrderId(@RequestBody UserOrders userOrders) {
        ordersService.deleteOrderByOrderId(userOrders.getId());
        return R.ok();
    }
    /*全部订单查询*/
    @RequestMapping("/findAllOrder")
    public List<GetOrders> findAllOrder(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        List<GetOrders> getOrders = ordersService.findAllOrder(userId);
        return getOrders;
    }
    /*已支付订单查询*/

    @RequestMapping("/payOrder")

    public List<GetOrders> payOrder(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        List<GetOrders> getOrders = ordersService.payOrder(userId);
        return getOrders;
    }

    /*wei支付订单查询*/

    @RequestMapping("/noPayOrder")

    public List<GetOrders> noPayOrder(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        List<GetOrders> getOrders = ordersService.noPayOrder(userId);
        return getOrders;
    }

}

