package com.neusoft.tijiancms.service;

import com.neusoft.tijiancms.po.OverallResult;

import java.util.List;

public interface OverallResultService {

    List<OverallResult> listOverallResultByOrderId(Integer orderId);

}
