package com.neusoft.tijiancms.mapper;

import com.neusoft.tijiancms.po.CheckItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckItemMapper {

    CheckItem getCheckItemById(Integer ciId);
}
