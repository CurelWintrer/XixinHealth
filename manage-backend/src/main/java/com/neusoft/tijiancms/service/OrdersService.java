package com.neusoft.tijiancms.service;

import com.neusoft.tijiancms.dto.OrdersPageRequestDto;
import com.neusoft.tijiancms.dto.OrdersPageResponseDto;
import com.neusoft.tijiancms.po.Orders;

public interface OrdersService {

    OrdersPageResponseDto listOrders(OrdersPageRequestDto request);

    Orders getOrdersById(Integer orderId);

    int updateOrdersState(Orders orders);
}
