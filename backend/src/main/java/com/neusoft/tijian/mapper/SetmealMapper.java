package com.neusoft.tijian.mapper;

import com.neusoft.tijian.po.Setmeal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealMapper {
    //查询体检套餐列表 包含检查项信息
    List<Setmeal> listSetmealByType(Integer type);
}
