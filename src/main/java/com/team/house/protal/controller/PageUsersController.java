package com.team.house.protal.controller;

import com.team.house.entity.Users;
import com.team.house.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/")
public class PageUsersController {
    @Autowired
    private UsersService usersService;

    //checkname
    @RequestMapping("checkname")
    @ResponseBody
    public String checkname(String name) {
        int usersByName = usersService.getUsersByName(name);
        return "{\"result\":" + usersByName + "}";
    }

    //注册
    @RequestMapping("regs")
    public String regs(Users users) {
        Integer integer = usersService.addUsers(users);
        if (integer > 0) {
            return "login";
        } else {
            return "regs";
        }
    }

    //登录
    @RequestMapping("login")
    public String login(Users users, Model model, HttpSession session) {
        Users user = usersService.getUsersByUsers(users);
        if (user == null) {
            model.addAttribute("info", "登录失败哦");
            return "login";
        } else {
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(200);//200秒
            return "redirect:getHouse";
        }
    }
}