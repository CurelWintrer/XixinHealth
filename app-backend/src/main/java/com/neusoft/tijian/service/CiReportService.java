package com.neusoft.tijian.service;

import com.neusoft.tijian.po.CiReport;

import java.util.List;

public interface CiReportService {
    List<CiReport> listCiReport(Integer orderId);
}
