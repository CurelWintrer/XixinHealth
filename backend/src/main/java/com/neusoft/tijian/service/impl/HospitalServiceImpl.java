package com.neusoft.tijian.service.impl;

import com.neusoft.tijian.mapper.HospitalMapper;
import com.neusoft.tijian.po.Hospital;
import com.neusoft.tijian.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalMapper hospitalMapper;

    @Override
    public List<Hospital> listHospital(Integer state) {
        return hospitalMapper.listHospital(state);
    }
}
