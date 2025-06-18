package com.neusoft.tijiancms.service;

import com.neusoft.tijiancms.po.OverallResult;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OverallResultService {

    List<OverallResult> listOverallResultByOrderId(Integer orderId);

    int saveOverallResult(OverallResult overallResult);

    int updateOverallResult(OverallResult overallResult);

    int removeOverallResult(Integer orId);

}
