package com.neusoft.tijiancms.mapper;

import com.neusoft.tijiancms.po.SetmealDetailed;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDetailedMapper {

    List<SetmealDetailed> listSetmealDetailedBySmId(Integer smId);
}
