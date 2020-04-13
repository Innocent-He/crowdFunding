package com.hgy.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.hgy.crowd.constant.CrowdConstant;
import com.hgy.crowd.entity.Admin;
import com.hgy.crowd.exception.ErrorDeleteException;
import com.hgy.crowd.service.AdminService;
import com.hgy.crowd.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author He
 * @Date 2020/3/31 10:12
 * @Version 1.0
 */
@Controller
public class AdminHandler {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session){
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }
    @RequestMapping("/admin/do/login.html")
    public String doLogin(@RequestParam("loginAcct") String loginAcct,
                          @RequestParam("userPswd") String userPswd,
                          HttpSession session){
           Admin admin= adminService.getAdmintByLoginAcct(loginAcct,userPswd);
           session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);
           return "redirect:/admin/to/main/page.html";
    }
    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(@RequestParam(value = "keyWord",defaultValue = "") String keyWord,
                              @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNo,
                              @RequestParam(value = "pageSize",defaultValue = "16") Integer pageSize,
                              ModelMap map
                             ){
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyWord, pageNo, pageSize);
        map.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,pageInfo);
        return "admin-page";
    }
    @RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyWord}.html")
    public String remove(@PathVariable("adminId") Integer adminId,
                         @PathVariable("pageNum") Integer pageNum,
                         @PathVariable("keyWord") String keyWord,
                         HttpSession session){
        Admin admin =(Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        if (adminId.equals(admin.getId())){
            throw new ErrorDeleteException("不可以删除正在登陆的账户");
        }
        Integer integer = adminService.deleteById(adminId);
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyWord"+keyWord;
    }
    @RequestMapping("/admin/save/page.html")
    public String save(Admin admin){
        adminService.savaAdmin(admin);
        return "redirect:/admin/get/page.html";
    }

    @RequestMapping("/admin/toUpdate/{adminId}.html")
    public String toUpdate(@PathVariable("adminId") Integer adminId,
                         ModelMap mp){
        Admin admin = adminService.queryAdminById(adminId);
        mp.addAttribute("admin",admin);
        return "admin-update";
    }
    @RequestMapping("/admin/update.html")
    public String update(Admin admin){
        adminService.updateAdmin(admin);
        return "redirect:/admin/get/page.html";
    }

}
