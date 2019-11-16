package com.fff.service.impl;

import com.fff.dao.UserOrdersMapper;
import com.fff.dao.UserOrdersRepository;
import com.fff.domain.UserOrders;
import com.fff.commons.GetOrders;
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
    public void updateStatus(String orderNum) {
        userOrdersMapper.updateStatus(orderNum);
    }

    @Override
    public UserOrders findByOrderNum(String orderNum) {
        UserOrders byOrderNum = userOrdersRepository.findByOrderNum(orderNum);
        return byOrderNum;
    }

    @Override
    public Double findPriceByUserId(Integer userId) {
        return userOrdersMapper.findPriceByUserId(userId);
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
    public void addOrder(UserOrders userOrders) {
        userOrdersRepository.save(userOrders);
    }
}
