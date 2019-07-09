package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Users;
import com.team.house.entity.UsersExample;
import com.team.house.mapper.UsersMapper;
import com.team.house.service.UsersService;
import com.team.house.util.MD5Utils;
import com.team.house.util.UsersParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public PageInfo<Users> getUsersByPage(UsersParam usersParam) {
        PageHelper.startPage(usersParam.getPage(), usersParam.getRows());
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        //放条件
        if (usersParam.getName() != null) {
            criteria.andNameLike("%" + usersParam.getName() + "%");
        }
        if (usersParam.getTelephone() != null) {
            criteria.andTelephoneLike("%" + usersParam.getTelephone() + "%");
        }
        List<Users> list = usersMapper.selectByExample(example);
        PageInfo<Users> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public Integer addUsers(Users users) {
        users.setIsadmin(0);
        //密码加密
        String password = MD5Utils.md5Encrypt(users.getPassword());
        users.setPassword(password);
        return usersMapper.insertSelective(users);
    }

    @Override
    public Users getUsersById(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer upUsers(Users users) {
        return usersMapper.updateByPrimaryKeySelective(users);
    }

    @Override
    public Integer deleteUsers(Integer id) {
        return usersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteMoreUsers(Integer[] arr) {
        return usersMapper.deleteMoreUsers(arr);
    }

    //按条件查询
    @Override
    public int getUsersByName(String name) {
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();

        criteria.andNameEqualTo(name);
        criteria.andIsadminEqualTo(0);

        List<Users> list = usersMapper.selectByExample(example);
        return list.size();
    }

    //登录
    @Override
    public Users getUsersByUsers(Users users) {
        //加密
        String password = MD5Utils.md5Encrypt(users.getPassword());
        UsersExample example = new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();

        criteria.andNameEqualTo(users.getName());
        criteria.andPasswordEqualTo(password);
        criteria.andIsadminEqualTo(0);

        List<Users> list = usersMapper.selectByExample(example);
        //判断
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }
}