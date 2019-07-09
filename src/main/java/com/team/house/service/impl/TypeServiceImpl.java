package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Type;
import com.team.house.entity.TypeExample;
import com.team.house.mapper.TypeMapper;
import com.team.house.service.TypeService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public PageInfo<Type> getTypeByPage(PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(), pageUtil.getRows());
        TypeExample example = new TypeExample();
        List<Type> list = typeMapper.selectByExample(example);
        PageInfo<Type> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public Integer addType(Type type) {
        return typeMapper.insertSelective(type);
    }

    @Override
    public Type getTypeById(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer upType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    public Integer deleteType(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteMoreType(Integer[] arr) {
        return typeMapper.deleteMoreType(arr);
    }

    //全部类型
    @Override
    public List<Type> getTypeList() {
        return typeMapper.selectByExample(null);
    }
}

