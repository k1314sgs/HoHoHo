package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.service.UsersService;
import com.team.house.util.PageUtil;
import com.team.house.util.UsersParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin/")
@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("getUsers")
    @ResponseBody
    public Map<String, Object> getUsers(PageUtil pageUtil, UsersParam usersParam) {
        PageInfo<Users> info = usersService.getUsersByPage(usersParam);
        //存
        Map<String, Object> map = new HashMap<>();
        map.put("total", info.getTotal());
        map.put("rows", info.getList());
        return map;
    }

    //添加
    @RequestMapping("addUsers")
    @ResponseBody
    public String add(Users users) {
        Integer integer = usersService.addUsers(users);

        return "{\"result\":" + integer + "}";
    }

    //查询单条
    @RequestMapping("getUsersById")
    @ResponseBody
    public Users getUsersById(Integer id) {
        Users users = usersService.getUsersById(id);

        return users;
    }

    //修改
    @RequestMapping(value = "upUsers", produces = "application/json;charset=utf8")
    @ResponseBody
    public String upUsers(Users users) {
        Integer integer = usersService.upUsers(users);

        return "{\"result\":" + integer + "}";

    }

    //单条删除
    @RequestMapping("deleteUsers")
    @ResponseBody
    public Integer deleteUsers(Integer id) {
        Integer integer = usersService.deleteUsers(id);

        return integer;
    }

    //批量删除
    @RequestMapping("deleteMoreUsers")
    @ResponseBody
    public String deleteMoreUsers(Integer[] ids) {
        Integer integer = usersService.deleteMoreUsers(ids);

        return " {\"result\":"+integer+"}";
    }

}
