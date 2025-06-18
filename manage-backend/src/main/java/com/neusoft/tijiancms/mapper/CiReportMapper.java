package com.neusoft.tijiancms.mapper;

import com.neusoft.tijiancms.po.CiReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CiReportMapper {

    @Select("select count(*) from cireport where orderId=#{orderId}")
    int getCiReportByOrderId(Integer orderId);

    int saveCiReport(List<CiReport> list);

    @Select("select * from cireport where orderId=#{orderId}")
    List<CiReport> listCiReport(Integer orderId);
}
