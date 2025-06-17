package com.neusoft.tijian.controller;


import com.neusoft.tijian.po.Users;
import com.neusoft.tijian.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/getUsersByUserIdByPass")
    public Users getUsersByUserIdByPass(@RequestBody Users users) {
        return usersService.getUsersByUserIdByPass(users);
    }

    @RequestMapping("/getUsersById")
    public Users getUsersById(@RequestBody Users users) {
        return usersService.getUsersById(users.getUserId());
    }

    @RequestMapping("/saveUsers")
    public Integer saveUsers(@RequestBody Users users) {
         return  usersService.saveUsers(users);
    }
}
