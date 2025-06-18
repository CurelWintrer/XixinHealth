package com.neusoft.tijiancms.service.impl;

import com.neusoft.tijiancms.mapper.CiDetailedReportMapper;
import com.neusoft.tijiancms.po.CiDetailedReport;
import com.neusoft.tijiancms.service.CiDetailedReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiDetailedReportServiceImpl implements CiDetailedReportService {
    @Autowired
    private CiDetailedReportMapper ciDetailedReportMapper;

    @Override
    public int updateCiDetailedReport(List<CiDetailedReport> list) {
        return ciDetailedReportMapper.updateCiDetailedReport(list);
    }
}
