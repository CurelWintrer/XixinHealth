package com.neusoft.tijian.mapper;

import com.neusoft.tijian.po.CheckItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CheckItemMapper {

    @Select("select * from checkItem where ciId=#{ciId}")
    CheckItem getCheckItemById(Integer ciId);

}
