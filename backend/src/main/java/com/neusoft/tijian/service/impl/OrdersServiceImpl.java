package com.neusoft.tijian.service.impl;

import com.neusoft.tijian.mapper.OrdersMapper;
import com.neusoft.tijian.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public int getOrdersByUserId(String userId) {
        return ordersMapper.getOrdersByUserId(userId);
    }
}
