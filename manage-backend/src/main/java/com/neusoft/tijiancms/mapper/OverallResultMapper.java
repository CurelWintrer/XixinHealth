package com.neusoft.tijiancms.mapper;

import com.neusoft.tijiancms.po.OverallResult;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OverallResultMapper {

    @Select("select  * from overallresult where orderId=#{orderId} order by orderId")
    List<OverallResult> listOverallResultByOrderId(Integer orderId);

    @Insert("insert into overallresult values(null,#{title},#{content},#{orderId})")
    int saveOverallResult(OverallResult overallResult);

    @Update("update overallresult set title=#{title},content=#{content} where orId=#{orId}")
    int updateOverallResult(OverallResult overallResult);

    @Delete("delete from overallresult where orId=#{orId}")
    int removeOverallResult(Integer orId);
}
