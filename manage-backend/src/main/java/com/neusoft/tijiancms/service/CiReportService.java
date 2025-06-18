package com.neusoft.tijiancms.service;

import com.neusoft.tijiancms.po.CiReport;
import com.neusoft.tijiancms.po.Orders;

import java.util.List;

public interface CiReportService {

    int createReportTemplate(Orders orders);

    List<CiReport>  listCiReport(Integer orderId);

}
