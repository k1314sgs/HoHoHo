package com.team.house.protal.controller;

import com.team.house.entity.Type;
import com.team.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/page/")
@Controller
public class PageTypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("getTypeList")
    @ResponseBody
    public List<Type> getTypeList() {
        return typeService.getTypeList();
    }
}
