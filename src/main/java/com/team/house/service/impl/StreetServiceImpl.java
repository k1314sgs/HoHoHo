package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.entity.StreetExample;
import com.team.house.mapper.StreetMapper;
import com.team.house.service.StreetService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;

    @Override
    public Integer deleteStreetById(Integer id) {
        return streetMapper.deleteStreetById(id);
    }

    @Override
    public PageInfo<Street> getStreet(Integer id, PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(), pageUtil.getRows());
        StreetExample example = new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        //条件
        criteria.andDistrictIdEqualTo(id);
        List<Street> list = streetMapper.selectByExample(example);
        PageInfo<Street> info = new PageInfo<>(list);
        return info;
    }

    //动态显示街道
    @Override
    public List<Street> getStreetByDid(Integer did) {
        StreetExample example = new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(did);

        return streetMapper.selectByExample(example);
    }
}
