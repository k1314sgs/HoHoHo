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
    public String login(String inputCode, Users users, Model model, HttpSession session) {
        //取出之前储存的短信验证码，和用户输入的作对比
        String code = (String) session.getAttribute("code");
        if (code == null) {
            model.addAttribute("info", "验证码过期，请重新获取");
            return "login";
        } else {
            if (code.equals(inputCode)) {
                Users user = usersService.getUsersByUsers(users);
                if (user == null) {
                    model.addAttribute("info", "用户名或密码不正确");
                    return "login";
                } else {
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(200);//200秒
                    return "redirect:getHouse";
                }
            } else {
                model.addAttribute("info", "验证码输入错误，请重新输入");
                return "login";
            }
        }
    }


}