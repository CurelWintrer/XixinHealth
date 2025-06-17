package com.neusoft.tijian.controller;

import com.neusoft.tijian.po.Orders;
import com.neusoft.tijian.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/getOrdersByUserId")
    public int getOrdersByUserId(@RequestBody Orders orders){
        return ordersService.getOrdersByUserId(orders.getUserId());
    }
}
