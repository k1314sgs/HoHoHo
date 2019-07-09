package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.service.DistrictService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin/")
@Controller
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @RequestMapping("getDistrict")
    @ResponseBody
    public Map<String, Object> getDistrict(PageUtil pageUtil) {
        PageInfo<District> info = districtService.getDistrictByPage(pageUtil);
        //存
        Map<String, Object> map = new HashMap<>();
        map.put("total", info.getTotal());
        map.put("rows", info.getList());
        return map;
    }

    //添加
    @RequestMapping("addDistrict")
    @ResponseBody
    public String add(District district) {
        Integer integer = districtService.addDistrict(district);

        return "{\"result\":" + integer + "}";
    }

    //查询单条
    @RequestMapping("getDistrictById")
    @ResponseBody
    public District getDistrictById(Integer id) {
        District district = districtService.getDistrictById(id);

        return district;
    }

    //修改
    @RequestMapping(value = "upDistrict", produces = "application/json;charset=utf8")
    @ResponseBody
    public String upDistrict(District district) {
        Integer integer = districtService.upDistrict(district);

        return "{\"result\":" + integer + "}";

    }

    //单条删除
    @RequestMapping("deleteDistrict")
    @ResponseBody
    public Integer deleteDistrict(Integer id) {
        Integer integer = districtService.deleteDistrict(id);

        return integer;
    }

    //批量删除
    @RequestMapping("deleteMoreDistrict")
    @ResponseBody
    public String deleteMoreDistrict(Integer[] ids) {
        Integer integer = districtService.deleteMoreDistrict(ids);

        return " {\"result\":"+integer+"}";
    }

}
