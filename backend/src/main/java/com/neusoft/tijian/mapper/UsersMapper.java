package com.neusoft.tijian.mapper;

import com.neusoft.tijian.po.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UsersMapper {
    //login
    @Select("select * from users where userId=#{userId} and password=#{password}")
    Users getUsersByUserIdByPass(Users users);
}
