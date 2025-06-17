package com.neusoft.tijian.mapper;

import com.neusoft.tijian.po.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UsersMapper {
    //login
    @Select("select * from users where userId=#{userId} and password=#{password}")
    Users getUsersByUserIdByPass(Users users);

    //验证电话号码是否存在
    @Select("select * from users where userId=#{userId}")
    Users getUsersById(String userId);

    //register
    @Select("insert into users values(#{userId},#{password},#{realName},#{sex},#{identityCard},#{birthday},#{userType})")
    Integer saveUsers(Users users);
}
