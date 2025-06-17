package com.neusoft.tijiancms.mapper;

import com.neusoft.tijiancms.po.Doctor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DoctorMapper {

    @Select("select * from doctor where docCode=#{docCode} and password=#{password}")
    Doctor getDoctorByCodeByPass(Doctor doctor);


}
