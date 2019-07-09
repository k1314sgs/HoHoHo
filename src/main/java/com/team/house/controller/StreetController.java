package com.team.house.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.Street;
import com.team.house.service.StreetService;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin/")
@Controller
public class StreetController {
    @Autowired
    private StreetService streetService;

    @RequestMapping("getStreet")
    @ResponseBody
    public Map<String, Object> getDistrict(Integer id, PageUtil pageUtil) {
        PageInfo<Street> info = streetService.getStreet(id, pageUtil);
        //存
        Map<String, Object> map = new HashMap<>();
        map.put("total", info.getTotal());
        map.put("rows", info.getList());
        return map;
    }

    @RequestMapping("deleteStreet")
    @ResponseBody
    public String deleteStreet(Integer id) {
        Integer integer = streetService.deleteStreetById(id);

        return " {\"result\":" + integer + "}";
    }
}
