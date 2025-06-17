package com.neusoft.tijian.mapper;

import com.neusoft.tijian.po.CiDetailedReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CiDetailedReportMapper {

    @Select("select * from cidetailedreport where orderId=#{orderId} and ciId=#{ciId}")
    List<CiDetailedReport> listCiDetailedReportByOrderIdByCiId(CiDetailedReport ciDetailedReport);
}
