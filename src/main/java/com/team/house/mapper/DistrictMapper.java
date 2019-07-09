package com.team.house.mapper;

import com.team.house.entity.District;
import com.team.house.entity.DistrictExample;

import java.util.List;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    //自定义的方法
    int deleteMoreDistrict(Integer[] arr);

}