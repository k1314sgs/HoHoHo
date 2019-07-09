package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.util.PageUtil;

import java.util.List;

public interface StreetService {

    Integer deleteStreetById(Integer id);

    PageInfo<Street> getStreet(Integer id, PageUtil pageUtil);

    List<Street>getStreetByDid(Integer did);
}
