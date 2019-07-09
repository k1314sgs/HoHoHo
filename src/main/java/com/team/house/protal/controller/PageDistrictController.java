package com.team.house.protal.controller;

import com.team.house.entity.District;
import com.team.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/page/")
@Controller
public class PageDistrictController {
    @Autowired
    private DistrictService districtService;

    @RequestMapping("getDistrictList")
    @ResponseBody
    public List<District> getDistrictList() {
        return districtService.getDistrictList();
    }
}
