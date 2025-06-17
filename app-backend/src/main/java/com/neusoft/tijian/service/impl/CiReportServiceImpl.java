package com.neusoft.tijian.service.impl;

import com.neusoft.tijian.mapper.CiDetailedReportMapper;
import com.neusoft.tijian.mapper.CiReportMapper;
import com.neusoft.tijian.po.CiDetailedReport;
import com.neusoft.tijian.po.CiReport;
import com.neusoft.tijian.service.CiReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiReportServiceImpl implements CiReportService {

    @Autowired
    private CiReportMapper ciReportMapper;

    @Autowired
    private CiDetailedReportMapper ciDetailedReportMapper;

    @Override
    public List<CiReport> listCiReport(Integer orderId) {
        //先查询CiReport
        List<CiReport> crList = ciReportMapper.listCiReport(orderId);

        //遍历所有检查项 依次查询明细
        for(CiReport cr : crList){
            CiDetailedReport param = new CiDetailedReport();
            param.setOrderId(orderId);
            param.setCiId(cr.getCiId());
            List<CiDetailedReport> list = ciDetailedReportMapper.listCiDetailedReportByOrderIdByCiId(param);
            cr.setCidrList(list);
        }

        return null;
    }
}
