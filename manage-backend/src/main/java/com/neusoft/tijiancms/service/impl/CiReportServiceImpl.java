package com.neusoft.tijiancms.service.impl;

import com.neusoft.tijiancms.mapper.CiDetailedReportMapper;
import com.neusoft.tijiancms.mapper.CiReportMapper;
import com.neusoft.tijiancms.mapper.SetmealMapper;
import com.neusoft.tijiancms.po.*;
import com.neusoft.tijiancms.service.CiReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiReportServiceImpl implements CiReportService {

    @Autowired
    private CiReportMapper ciReportMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private CiDetailedReportMapper ciDetailedReportMapper;

    //事务处理注解 需要对多表增删改查
    @Transactional
    @Override
    public int createReportTemplate(Orders orders) {
        //检查预约编号对应的体检报告是否已经生成 已经生成则返回
        int count = ciReportMapper.getCiReportByOrderId(orders.getOrderId());
        if (count > 0) {
            return 1;
        }

        //查询报告模板 从CheckItem和CheckItemDetailed查询检查项和检查项明细
        Setmeal setmeal = setmealMapper.getSetmealById(orders.getSmId());

        //根据查询出的信息 先向CiReport中插入报告检查项模板
        List<CiReport> cirList = new ArrayList<>();

        for (SetmealDetailed sd : setmeal.getSdList()) {
            CiReport cir = new CiReport();
            cir.setCirId(sd.getCheckItem().getCiId());
            cir.setCirName(sd.getCheckItem().getCiName());
            cir.setOrderId(orders.getOrderId());
            cirList.add(cir);
        }

        int result1 = ciReportMapper.saveCiReport(cirList);

        //根据查询出的信息 先向CiDetailedReport中插入报告检查项明细模板
        List<CiDetailedReport> cidrList = new ArrayList<>();
        for (SetmealDetailed sd : setmeal.getSdList()){
            for(CheckItemDetailed cid : sd.getCheckItem().getCdList()){
                CiDetailedReport cidr = new CiDetailedReport();
                cidr.setName(cid.getName());
                cidr.setUnit(cid.getUnit());
                cidr.setMinrange(cid.getMinrange());
                cidr.setMaxrange(cid.getMaxrange());
                cidr.setNormalValue(cid.getNormalValue());
                cidr.setNormalValueString(cid.getNormalValueString());
                cidr.setType(cid.getType());
                cidr.setValue("");
                cidr.setIsError(0);
                cidr.setCiId(sd.getCiId());
                cidr.setOrderId(orders.getOrderId());
                cidrList.add(cidr);
            }
        }

        int result2 = ciDetailedReportMapper.saveCiDetailedReport(cidrList);

        return result1>0&&result2>0?1:0;
    }
}
