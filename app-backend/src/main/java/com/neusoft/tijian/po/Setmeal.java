package com.neusoft.tijian.po;

import java.util.List;

public class Setmeal {
    private Integer smId;
    private String name;
    private Integer type;
    private Integer price;

    private List<SetmealDetailed> sdList;

    public List<SetmealDetailed> getSdList() {
        return sdList;
    }

    public void setSdList(List<SetmealDetailed> sdList) {
        this.sdList = sdList;
    }

    public Integer getSmId() {
        return smId;
    }

    public void setSmId(Integer smId) {
        this.smId = smId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
