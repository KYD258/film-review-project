package com.fff.controller;

import com.fff.commons.R;
import com.fff.domain.UserOrders;
import com.fff.commons.GetOrders;
import com.fff.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public R addOrder(@RequestBody UserOrders userOrders) {
        ordersService.addOrder(userOrders);
        return R.ok();
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

    /*未支付订单查询*/

    @RequestMapping("/noPayOrder")

    public List<GetOrders> noPayOrder(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        List<GetOrders> getOrders = ordersService.noPayOrder(userId);
        return getOrders;
    }

    /*订单修改*/
    @RequestMapping("/updateOrder")
    public UserOrders updateOrder(UserOrders userOrders){
        String orderNum = userOrders.getOrderNum();
        ordersService.updateStatus(orderNum);
        return userOrders;
    }

    /*根据订单号查订单*/
    @RequestMapping("/findByOrderNum")
    public UserOrders findByOrderNum(String orderNum){
        return ordersService.findByOrderNum(orderNum);
    }

    /*根据用户id查价格*/
    @RequestMapping("/findPriceByUserId")
    public Double findPriceByUserId(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        return ordersService.findPriceByUserId(userId);
    }

}

