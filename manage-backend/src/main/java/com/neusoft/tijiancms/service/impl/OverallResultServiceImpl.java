package com.neusoft.tijiancms.service.impl;

import com.neusoft.tijiancms.mapper.OverallResultMapper;
import com.neusoft.tijiancms.po.OverallResult;
import com.neusoft.tijiancms.service.OverallResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverallResultServiceImpl implements OverallResultService {

    @Autowired
    OverallResultMapper overallResultMapper;

    @Override
    public List<OverallResult> listOverallResultByOrderId(Integer orderId) {
        return overallResultMapper.listOverallResultByOrderId(orderId);
    }

    @Override
    public int saveOverallResult(OverallResult overallResult) {
        return overallResultMapper.saveOverallResult(overallResult);
    }

    @Override
    public int updateOverallResult(OverallResult overallResult) {
        return overallResultMapper.updateOverallResult(overallResult);
    }

    @Override
    public int removeOverallResult(Integer orId) {
        return overallResultMapper.removeOverallResult(orId);
    }
}
