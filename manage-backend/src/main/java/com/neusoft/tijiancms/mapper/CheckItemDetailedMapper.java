package com.neusoft.tijiancms.mapper;

import com.neusoft.tijiancms.po.CheckItemDetailed;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CheckItemDetailedMapper {

    @Select("select * from checkitemdetailed where ciId=#{ciId} order by ciId")
    List<CheckItemDetailed> listCheckItemDetailed(Integer ciId);
}
