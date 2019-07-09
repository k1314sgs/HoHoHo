package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.util.UsersParam;

public interface UsersService {
    PageInfo<Users> getUsersByPage(UsersParam usersParam);

    Integer addUsers(Users users);

    Users getUsersById(Integer id);

    Integer upUsers(Users users);

    Integer deleteUsers(Integer id);

    //自定义的
    Integer deleteMoreUsers(Integer[] arr);

    //按条件查询
    int getUsersByName(String name);

    //登录
    Users getUsersByUsers(Users users);
}
