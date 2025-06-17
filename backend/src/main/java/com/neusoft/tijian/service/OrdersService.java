package com.neusoft.tijian.service;

import com.neusoft.tijian.po.Orders;

import java.util.List;

public interface OrdersService {
    int getOrdersByUserId(String userId);
    int saveOrders(Orders orders);
    List<Orders> listOrdersByUserId(String userId);
    int removeOrders(Integer orderId);
}
