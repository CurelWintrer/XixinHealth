package com.neusoft.tijian.mapper;

import com.neusoft.tijian.po.CiReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CiReportMapper {

    @Select("select * from cireport where orderId=#{orderId} order by cirId")
    List<CiReport> listCiReport(Integer orderId);
}
