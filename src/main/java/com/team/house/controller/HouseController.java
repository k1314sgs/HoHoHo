package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.service.HouseService;
import com.team.house.util.HouseParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin/")
@RestController("houseController2")//相当于responsebody和controller的合体
public class HouseController {
    @Autowired
    private HouseService houseService;

    /*//是否审核,无条件的
    @RequestMapping("getHouseByNoPass")
    public Map<String, Object> getHouseByNoPass(PageUtil pageUtil, Integer ispass) {
        PageInfo<House> info = houseService.getHouseByIspass(ispass, pageUtil);
        Map<String, Object> map = new HashMap<>();
        map.put("total", info.getTotal());
        map.put("rows", info.getList());
        return map;
    }*/

    //审核
    @RequestMapping("houseYes")
    public Integer houseYes(String id) {
        return houseService.housePass(1, id);
    }

    //搜索查询
    @RequestMapping("searchHouse")
    public Map<String, Object> searchHouse(HouseParam houseParam) {
        PageInfo<House> info = houseService.searchHouse(houseParam);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", info.getList());
        map.put("total", info.getTotal());
        return map;
    }

    //查询详细信息

}
