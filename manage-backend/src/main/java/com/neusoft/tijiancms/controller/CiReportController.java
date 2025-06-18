package com.neusoft.tijiancms.controller;

import com.neusoft.tijiancms.po.Orders;
import com.neusoft.tijiancms.service.CiReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ciReport")
public class CiReportController {

    @Autowired
    private CiReportService ciReportService;

    @RequestMapping("/createReportTemplate")
    public int createReportTemplate(@RequestBody Orders orders) {
        return ciReportService.createReportTemplate(orders);
    }

}
