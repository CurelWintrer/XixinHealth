package com.neusoft.tijiancms.controller;

import com.neusoft.tijiancms.dto.OrdersPageRequestDto;
import com.neusoft.tijiancms.dto.OrdersPageResponseDto;
import com.neusoft.tijiancms.po.Orders;
import com.neusoft.tijiancms.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/listOrders")
    public OrdersPageResponseDto listOrders(@RequestBody OrdersPageRequestDto request) {
        return ordersService.listOrders(request);
    }


    @RequestMapping("/getOrdersById")
    public Orders getOrdersById(@RequestBody Orders orders) {
        return ordersService.getOrdersById(orders.getOrderId());
    }

    @RequestMapping("/updateOrdersState")
    public int updateOrdersState(Orders orders) {
        return ordersService.updateOrdersState(orders);
    }
}
