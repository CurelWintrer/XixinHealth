package com.neusoft.tijiancms.mapper;

import com.neusoft.tijiancms.po.CiDetailedReport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CiDetailedReportMapper {

    int saveCiDetailedReport(List<CiDetailedReport> list);
}
