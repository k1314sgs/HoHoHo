package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.util.PageUtil;

import java.util.List;

public interface TypeService {
    PageInfo<Type> getTypeByPage(PageUtil pageUtil);

    Integer addType(Type type);

    Type getTypeById(Integer id);

    Integer upType(Type type);

    Integer deleteType(Integer id);

    //自定义的
    Integer deleteMoreType(Integer[] arr);

    //查询全部类型
    List<Type> getTypeList();
}
