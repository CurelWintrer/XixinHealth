package com.neusoft.tijiancms.service;

import com.neusoft.tijiancms.dto.OrdersPageRequestDto;
import com.neusoft.tijiancms.dto.OrdersPageResponseDto;

public interface OrdersService {

    OrdersPageResponseDto listOrders(OrdersPageRequestDto request);
}
