package com.neusoft.tijiancms.controller;


import com.neusoft.tijiancms.po.OverallResult;
import com.neusoft.tijiancms.service.OverallResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/overallresult")
public class OverallResultController {

    @Autowired
    private OverallResultService overallResultService;

    @RequestMapping("/listOverallResultByOrderId")
    public List<OverallResult> listOverallResultByOrderId(@RequestBody OverallResult overallResult) {
        return overallResultService.listOverallResultByOrderId(overallResult.getOrderId());
    }

    @RequestMapping("/saveOverallResult")
    public int saveOverallResult(@RequestBody OverallResult overallResult) {
        return overallResultService.saveOverallResult(overallResult);
    }

    @RequestMapping("/updateOverallResult")
    public int updateOverallResult(@RequestBody OverallResult overallResult) {
        return overallResultService.updateOverallResult(overallResult);
    }

    @RequestMapping("/removeOverallResult")
    public int removeOverallResult(@RequestBody OverallResult overallResult) {
        return overallResultService.removeOverallResult(overallResult.getOrderId());
    }


}
