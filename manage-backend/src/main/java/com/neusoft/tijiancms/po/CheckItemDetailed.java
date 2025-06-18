package com.neusoft.tijiancms.po;

public class CheckItemDetailed {

    private Integer cdId;
    private String name;
    private String unit;
    private double minrange;
    private double maxrange;
    private String normalValue;
    private String normalValueString;
    private Integer type;
    private Integer ciId;
    private String remarks;

    public Integer getCdId() {
        return cdId;
    }

    public void setCdId(Integer cdId) {
        this.cdId = cdId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getMinrange() {
        return minrange;
    }

    public void setMinrange(double minrange) {
        this.minrange = minrange;
    }

    public double getMaxrange() {
        return maxrange;
    }

    public void setMaxrange(double maxrange) {
        this.maxrange = maxrange;
    }

    public String getNormalValue() {
        return normalValue;
    }

    public void setNormalValue(String normalValue) {
        this.normalValue = normalValue;
    }

    public String getNormalValueString() {
        return normalValueString;
    }

    public void setNormalValueString(String normalValueString) {
        this.normalValueString = normalValueString;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCiId() {
        return ciId;
    }

    public void setCiId(Integer ciId) {
        this.ciId = ciId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
