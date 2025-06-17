package com.neusoft.tijiancms.service.impl;

import com.neusoft.tijiancms.dto.OrdersPageRequestDto;
import com.neusoft.tijiancms.dto.OrdersPageResponseDto;
import com.neusoft.tijiancms.mapper.OrdersMapper;
import com.neusoft.tijiancms.po.Orders;
import com.neusoft.tijiancms.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public OrdersPageResponseDto listOrders(OrdersPageRequestDto request) {
        OrdersPageResponseDto response = new OrdersPageResponseDto();

        //获取总行数
        int totalRow = ordersMapper.getOrdersCount(request);
        response.setTotalRow(totalRow);

        //总行数为0
        if (totalRow == 0) {
            return response;
        }

        //计算总页数
        int totalPageNum = 0;
        if (totalRow % request.getMaxPageNum() == 0) {
            totalPageNum = totalRow / request.getMaxPageNum();
        } else {
            totalPageNum = totalRow / request.getMaxPageNum() + 1;
        }
        response.setTotalPageNum(totalPageNum);

        //计算上一页和下一页
        int pageNum = request.getPageNum();
        if (pageNum > 1) {
            response.setPreNum(pageNum - 1);
        }
        if (pageNum < totalPageNum) {
            response.setNextNum(pageNum + 1);
        }

        //计算开始查询记录数
        request.setBeginNum((pageNum - 1) * request.getMaxPageNum());

        //查询业务数据
        List<Orders> list = ordersMapper.listOrders(request);

        //给返回值填充余下数据
        response.setPageNum(pageNum);
        response.setMaxPageNum(request.getMaxPageNum());
        response.setList(list);

        return response;
    }
}
