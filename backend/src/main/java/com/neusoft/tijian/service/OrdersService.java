package com.neusoft.tijian.service;

import com.neusoft.tijian.po.Orders;

public interface OrdersService {
    int getOrdersByUserId(String userId);
    int saveOrders(Orders orders);
}
