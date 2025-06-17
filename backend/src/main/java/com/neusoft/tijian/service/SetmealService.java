package com.neusoft.tijian.service;

import com.neusoft.tijian.po.Setmeal;

import java.util.List;

public interface SetmealService {

    public List<Setmeal> listSetmealByType(Integer type);
}
