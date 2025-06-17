package com.neusoft.tijiancms.mapper;

import com.neusoft.tijiancms.po.Hospital;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HospitalMapper {

    //根据state查询医院
//    @Select("select * from hospital where state=#{state} order by hpId")
//    List<Hospital> listHospital(Integer state);

    //根据主键查询医院信息
    @Select("select * from hospital where hpId=#{hpId}")
    Hospital getHospitalById(Integer hpId);
}
