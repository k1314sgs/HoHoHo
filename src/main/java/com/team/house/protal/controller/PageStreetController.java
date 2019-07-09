package com.team.house.protal.controller;

import com.team.house.entity.Street;
import com.team.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/page/")
@Controller
public class PageStreetController {
    @Autowired
    private StreetService streetService;

    //动态显示街道
    @RequestMapping("getStreetByDid")
    @ResponseBody
    public List<Street> getStreetByDid(Integer did) {
        return streetService.getStreetByDid(did);
    }
}
