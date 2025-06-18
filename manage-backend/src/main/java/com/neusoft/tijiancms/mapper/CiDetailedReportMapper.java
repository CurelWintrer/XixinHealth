package com.neusoft.tijiancms.mapper;

import com.neusoft.tijiancms.po.CiDetailedReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CiDetailedReportMapper {

    int saveCiDetailedReport(List<CiDetailedReport> list);

    @Select("select  * from cidetailedreport where orderId=#{orderId} and ciId=#{ciId} order by cidrId")
    List<CiDetailedReport> listCiDetailedReportByOrderIdByCiId(CiDetailedReport ciDetailedReport);

}
