package com.neusoft.tijian.service;

import com.neusoft.tijian.po.Users;


public interface UsersService {

    Users getUsersByUserIdByPass(Users users);

    Users getUsersById(String userId);

    Integer saveUsers(Users users);
}
