package com.neusoft.tijian.mapper;

import com.neusoft.tijian.dto.CalendarRequestDto;
import com.neusoft.tijian.dto.CalendarResponseDto;
import com.neusoft.tijian.dto.OrdersMapperDto;
import com.neusoft.tijian.po.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrdersMapper {

    //查询是否预约过 有未归档预约记录的用户无法再次预约
    @Select("select count(*) from orders where state=1 and userId=#{userId}")
    int getOrdersByUserId(String userId);

    //根据parameList参数 查询30天的预约日期中 每一天的已预约人数
    List<CalendarResponseDto> listOrdersAppointmentNumber(List<OrdersMapperDto> list);

    //创建体检预约订单
    @Insert("insert into orders values(null,#{orderDate},#{userId},#{hpId},#{smId},1)")
    int saveOrders(Orders orders);

}
