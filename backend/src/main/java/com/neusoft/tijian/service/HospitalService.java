package com.neusoft.tijian.service;

import com.neusoft.tijian.po.Hospital;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HospitalService {

    public List<Hospital> listHospital(Integer state);
}
