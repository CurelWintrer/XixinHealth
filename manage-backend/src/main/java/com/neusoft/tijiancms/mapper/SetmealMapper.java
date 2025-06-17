package com.neusoft.tijiancms.mapper;

import com.neusoft.tijiancms.po.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {

    @Select("select * from setmeal order by smId")
    List<Setmeal> listSetmeal();

    //这是专门为Mapper中的listOrders方法做关联查询用的
    @Select("select * from setmeal where smId=#{smId}")
    Setmeal getSetmealByIdByMapper(Integer smId);


}
