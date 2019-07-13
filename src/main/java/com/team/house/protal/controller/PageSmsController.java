package com.team.house.protal.controller;

import com.team.house.sms.SmsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequestMapping("/page/")
@Controller
public class PageSmsController {

    //获取短信验证码
    @RequestMapping("getSmsCode")
    @ResponseBody
    public String getSmsCode(String telephone, HttpSession session) {
        //生成四位数字的验证码
        String code = (int) (Math.random() * 10000) + "";
        while (code.length() < 4) {
            code = "0" + code;
        }
        //发送短信
        String smsText = "验证码是：" + code + ",请在120秒内输入";
        int i = SmsUtil.sendSms(telephone, smsText);

        //巧妙保存验证码到session，2分钟失效
        session.setAttribute("code", code);
        session.setMaxInactiveInterval(120);
        return "{\"result\":" + i + "}";
    }
}