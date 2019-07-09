package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.util.PageUtil;

import java.util.List;

public interface DistrictService {
    PageInfo<District> getDistrictByPage(PageUtil pageUtil);

    Integer addDistrict(District district);

    District getDistrictById(Integer id);

    Integer upDistrict(District district);

    Integer deleteDistrict(Integer id);

    //自定义的
    Integer deleteMoreDistrict(Integer[] arr);

    //全部区域
    List<District> getDistrictList();
}
