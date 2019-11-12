package com.fff.service.impl;

import com.fff.dao.UserOrdersMapper;
import com.fff.dao.UserOrdersRepository;
import com.fff.domain.UserOrders;
import com.fff.responses.GetOrders;
import com.fff.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private UserOrdersRepository userOrdersRepository;
    @Autowired
    private UserOrdersMapper userOrdersMapper;

    @Override
    public List<UserOrders> findAll(Integer userId) {
        List<UserOrders> all = userOrdersRepository.findAll();
        return all;
    }

    @Override
    public List<GetOrders> noPayOrder(Integer userId) {
        List<GetOrders> getOrders = userOrdersMapper.noPayOrder(userId);
        return getOrders;
    }

    @Override
    public List<GetOrders> payOrder(Integer userId) {
        List<GetOrders> getOrders = userOrdersMapper.payOrder(userId);
        return getOrders;
    }

    @Override
    public List<GetOrders> findAllOrder(Integer userId) {
        List<GetOrders> allOrder = userOrdersMapper.findAllOrder(userId);
        return allOrder;
    }

    @Override
    public void deleteOrderByOrderId(Integer id) {
        userOrdersRepository.deleteById(id);
    }

    @Override
    public UserOrders addOrder(UserOrders userOrders) {
        UserOrders orders = userOrdersRepository.save(userOrders);
        return orders;
    }
}
