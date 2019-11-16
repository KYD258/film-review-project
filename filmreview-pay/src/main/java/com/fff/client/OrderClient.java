package com.fff.client;

import com.fff.commons.GetOrders;
import com.fff.domain.UserOrders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@FeignClient(value = "filmreview-order")
public interface OrderClient {

    @GetMapping("/order/addOrder")
    public UserOrders addOrder(@RequestBody UserOrders userOrders);
    @GetMapping("/order/findAllOrder")
    public List<GetOrders> findAllOrder(HttpSession session);
    @GetMapping("/order/updateOrder")
    public UserOrders updateOrder(@RequestBody UserOrders userOrders);
    @GetMapping("/order/findByOrderNum")
    public UserOrders findByOrderNum(String orderNum);
    @GetMapping("/order/findPriceByUserId")
    public Double findPriceByUserId(HttpSession session);
}
