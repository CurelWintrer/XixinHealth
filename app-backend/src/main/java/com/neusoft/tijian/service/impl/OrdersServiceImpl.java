package com.neusoft.tijian.service.impl;

import com.neusoft.tijian.mapper.OrdersMapper;
import com.neusoft.tijian.po.Orders;
import com.neusoft.tijian.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public int getOrdersByUserId(String userId) {
        return ordersMapper.getOrdersByUserId(userId);
    }

    @Override
    public int saveOrders(Orders orders) {
        return ordersMapper.saveOrders(orders);
    }

    @Override
    public List<Orders> listOrdersByUserId(Orders orders) {
        return ordersMapper.listOrdersByUserId(orders);
    }

    @Override
    public int removeOrders(Integer orderId) {
        return ordersMapper.removeOrders(orderId);
    }

    @Override
    public Orders getOrdersById(Integer orderId) {
        return ordersMapper.getOrdersById(orderId);
    }
}
