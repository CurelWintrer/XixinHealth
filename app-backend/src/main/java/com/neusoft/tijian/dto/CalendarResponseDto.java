package com.neusoft.tijian.dto;

//请求预约日历的响应数据
public class CalendarResponseDto {

    //预约日期
    private String ymd;
    //最大预约人数
    private Integer total;
    //现有预约人数
    private Integer existing;
    //剩余预约人数
    private Integer remainder;

    public CalendarResponseDto(){};

    public CalendarResponseDto(String ymd) {
        this.ymd = ymd;
    }

    public String getYmd() {
        return ymd;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getExisting() {
        return existing;
    }

    public void setExisting(Integer existing) {
        this.existing = existing;
    }

    public Integer getRemainder() {
        return remainder;
    }

    public void setRemainder(Integer remainder) {
        this.remainder = remainder;
    }
}
