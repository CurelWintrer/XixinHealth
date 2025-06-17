package com.neusoft.tijian.service.impl;

import com.neusoft.tijian.mapper.SetmealMapper;
import com.neusoft.tijian.po.Setmeal;
import com.neusoft.tijian.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public List<Setmeal> listSetmealByType(Integer type) {
        return setmealMapper.listSetmealByType(type);
    }
}
