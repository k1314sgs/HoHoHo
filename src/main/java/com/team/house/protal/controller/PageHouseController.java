package com.team.house.protal.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.District;
import com.team.house.entity.House;
import com.team.house.entity.Type;
import com.team.house.entity.Users;
import com.team.house.service.DistrictService;
import com.team.house.service.HouseService;
import com.team.house.service.TypeService;
import com.team.house.util.HouseParam;
import com.team.house.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@RequestMapping("/page/")
@Controller
public class PageHouseController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private DistrictService districtService;

    @Autowired
    private HouseService houseService;

    @RequestMapping("goFabu")
    public String goFabu(Model model) {
        List<Type> typeList = typeService.getTypeList();
        List<District> districtList = districtService.getDistrictList();
        model.addAttribute("typeList", typeList);
        model.addAttribute("districtList", districtList);
        return "fabu";
    }

    @RequestMapping("addHouse")
    public String addHouse(@RequestParam(value = "pfile", required = false) MultipartFile pfile,
                           House house,
                           HttpSession session) throws Exception {
        //得到文件名   1.jpg
        String filename = pfile.getOriginalFilename();
        //为了防止重名，改名吧骚年   .jsp
        String expname = filename.substring(filename.lastIndexOf("."));
        //时间.jsp
        String savename = System.currentTimeMillis() + expname;
        //创建路径
        String path = "D:/k9502/images/" + savename;
        //创建文件
        File file = new File(path);
        //创建目录
        if (!file.exists()) {
            file.mkdirs();
        }
        //上传图片完成
        pfile.transferTo(file);

        //将当前时间long设置为id
        house.setId(System.currentTimeMillis() + "");
        //设置用户id
        Users user = (Users) session.getAttribute("user");
        house.setUserId(user.getId());
        //设置图片
        house.setPath(savename);

        //调用业务存储
        int i = houseService.addHouse(house);
        if (i > 0) {
            return "redirect:getHouse";
        } else {
            file.delete();//删除文件
            return "redirect:goFabu";
        }
    }

    //按照uid查询用户
    @RequestMapping("getHouse")
    public String getHouse(PageUtil pageUtil, HttpSession session, Model model, Integer i) {
        Users user = (Users) session.getAttribute("user");
        Integer uid = user.getId();
        PageInfo<House> info = houseService.getHouseByUid(uid, pageUtil);
        //存
        model.addAttribute("info", info);
        if (i != null) {
            model.addAttribute("msg", i > 0 ? "修改成功" : "修改失败");
        }
        return "guanli";
    }

    //修改
    @RequestMapping("goUpdate")
    public String goUpdate(String id, Model model) {
        House house = houseService.getHouse(id);
        model.addAttribute("house", house);
        return "update";
    }

    //真正修改
    @RequestMapping("updateHouse")
    public String updateHouse(@RequestParam(value = "pfile", required = false) MultipartFile pfile,
                              House house) throws Exception {
        //得到文件名   1.jpg
        String filename = pfile.getOriginalFilename();
        //为了防止重名，改名吧骚年   .jsp

        if (!filename.equals("")) {
            String expname = filename.substring(filename.lastIndexOf("."));
            //时间.jsp
            String savename = System.currentTimeMillis() + expname;
            //创建路径
            String path = "D:/k9502/images/" + savename;
            //创建文件
            File file = new File(path);
            //创建目录
            if (!file.exists()) {
                file.mkdirs();
            }
            //上传图片完成
            pfile.transferTo(file);

            //删除旧图片
            new File(path).delete();
            //设置图片
            house.setPath(savename);
        }

        //调用业务存储
        int i = houseService.update(house);
        return "redirect:getHouse?i=" + i;
    }

    //逻辑删除
    @RequestMapping("deleteHouse")
    public String deleteHouse(String id) {
        houseService.deleteHouse(id, 1);
        return "redirect:getHouse";
    }

    //page前台搜索查询
    @RequestMapping("searchPage")
    public String searchPage(HouseParam houseParam, Model model) {
        PageInfo<House> info = houseService.searchPage(houseParam);
        model.addAttribute("info", info);
        model.addAttribute("houseParam", houseParam);
        return "list";
    }
}