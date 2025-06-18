package com.neusoft.tijiancms.mapper;

import com.neusoft.tijiancms.dto.OrdersPageRequestDto;
import com.neusoft.tijiancms.po.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrdersMapper {
    //根据条件查询预约订单总行数
    int getOrdersCount(OrdersPageRequestDto request);

    //根据条件分页查询
    List<Orders> listOrders(OrdersPageRequestDto request);

    //查询预约订单
    Orders getOrdersById(Integer orderId);

    @Update("update orders set state=#{state} where orderId={orderId}")
    int updateOrdersState(Orders orders);

}
