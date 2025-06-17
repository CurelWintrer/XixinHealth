package com.neusoft.tijian.service;

import com.neusoft.tijian.po.Hospital;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HospitalService {

    List<Hospital> listHospital(Integer state);

    Hospital getHospitalById(Integer hpId);
}
