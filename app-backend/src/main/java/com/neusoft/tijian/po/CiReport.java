package com.neusoft.tijian.po;

import java.util.List;

public class CiReport {

    private Integer cirId;
    private Integer ciId;
    private String cirName;
    private Integer orderId;

    private List<CiDetailedReport> cidrList;

    public Integer getCirId() {
        return cirId;
    }

    public void setCirId(Integer cirId) {
        this.cirId = cirId;
    }

    public Integer getCiId() {
        return ciId;
    }

    public void setCiId(Integer ciId) {
        this.ciId = ciId;
    }

    public String getCirName() {
        return cirName;
    }

    public void setCirName(String cirName) {
        this.cirName = cirName;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<CiDetailedReport> getCidrList() {
        return cidrList;
    }

    public void setCidrList(List<CiDetailedReport> cidrList) {
        this.cidrList = cidrList;
    }
}
