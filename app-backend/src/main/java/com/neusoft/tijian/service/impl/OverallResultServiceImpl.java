package com.neusoft.tijian.service.impl;

import com.neusoft.tijian.mapper.OverallResultMapper;
import com.neusoft.tijian.po.OverallResult;
import com.neusoft.tijian.service.OverallResultService;
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
}
