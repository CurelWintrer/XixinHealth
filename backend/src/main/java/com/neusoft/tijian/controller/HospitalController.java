package com.neusoft.tijian.controller;


import com.neusoft.tijian.po.Hospital;
import com.neusoft.tijian.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @RequestMapping("/listHospital")
    public List<Hospital> listHospital(@RequestBody Hospital hospital){
        return hospitalService.listHospital(hospital.getState());
    }

    @RequestMapping("/getHospitalById")
    public Hospital getHospitalById(@RequestBody Hospital hospital){
        return hospitalService.getHospitalById(hospital.getHpId());
    }

}
