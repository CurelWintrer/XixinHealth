package com.neusoft.tijian.mapper;

import com.neusoft.tijian.po.OverallResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OverallResultMapper {

    @Select("select  * from overallresult where orderId=#{orderId} order by orderId")
    List<OverallResult> listOverallResultByOrderId(Integer orderId);

}
