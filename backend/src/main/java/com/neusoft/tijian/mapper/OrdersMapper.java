package com.neusoft.tijian.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrdersMapper {

    //查询是否预约过 有未归档预约记录的用户无法再次预约
    @Select("select count(*) from orders where state=1 and userId=#{userId}")
    int getOrdersByUserId(String userId);


}
