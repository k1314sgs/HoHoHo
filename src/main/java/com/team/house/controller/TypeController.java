package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.service.TypeService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin/")
@Controller
public class TypeController {
    @Autowired
    private TypeService TypeService;

    @RequestMapping("getType")
    @ResponseBody
    public Map<String, Object> getType(PageUtil pageUtil) {
        PageInfo<Type> info = TypeService.getTypeByPage(pageUtil);
        //存
        Map<String, Object> map = new HashMap<>();
        map.put("total", info.getTotal());
        map.put("rows", info.getList());
        return map;
    }

    //添加
    @RequestMapping("addType")
    @ResponseBody
    public String add(Type type) {
        Integer integer = TypeService.addType(type);

        return "{\"result\":" + integer + "}";
    }

    //查询单条
    @RequestMapping("getTypeById")
    @ResponseBody
    public Type getTypeById(Integer id) {
        Type type = TypeService.getTypeById(id);

        return type;
    }

    //修改
    @RequestMapping(value = "upType", produces = "application/json;charset=utf8")
    @ResponseBody
    public String upType(Type type) {
        Integer integer = TypeService.upType(type);

        return "{\"result\":" + integer + "}";

    }

    //单条删除
    @RequestMapping("deleteType")
    @ResponseBody
    public Integer deleteType(Integer id) {
        Integer integer = TypeService.deleteType(id);

        return integer;
    }

    //批量删除
    @RequestMapping("deleteMoreType")
    @ResponseBody
    public String deleteMoreType(Integer[] ids) {
        Integer integer = TypeService.deleteMoreType(ids);

        return " {\"result\":"+integer+"}";
    }

}
