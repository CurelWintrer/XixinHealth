package com.neusoft.tijian.service;

import com.neusoft.tijian.po.Setmeal;

import java.util.List;

public interface SetmealService {

    List<Setmeal> listSetmealByType(Integer type);

    Setmeal getSetmealById(Integer smId);
}
