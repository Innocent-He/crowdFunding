package com.hgy.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.hgy.crowd.entity.Role;
import com.hgy.crowd.service.RoleService;
import com.hgy.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author He
 * @Date 2020/4/2 8:49
 * @Version 1.0
 */
@Controller
public class RoleHandler {
    @Autowired
    private RoleService roleService;
    @ResponseBody
    @RequestMapping("/role/get/page/info.json")
    public ResultEntity<PageInfo<Role>> getPageInfo(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "pageSize",defaultValue = "8") Integer pageSize,
                                                    @RequestParam(value = "keyWord",defaultValue = "") String keyWord){
        PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyWord);
        return ResultEntity.successWithData(pageInfo);
    }
    @ResponseBody
    @RequestMapping("/save/role.json")
    public ResultEntity<String> saveRole(Role role){
        roleService.saveRole(role);
        return  ResultEntity.successWithoutData();
    }
    @ResponseBody
    @RequestMapping("/query/role.json")
    public ResultEntity<String> queryRole(@RequestParam("id") Integer id){
        Role role=roleService.queryRoleById(id);
        return ResultEntity.successWithData(role.getName());
    }
    @ResponseBody
    @RequestMapping("/update/role.json")
    public ResultEntity<String> updateRole(Role role){
        roleService.updateRole(role);
        return ResultEntity.successWithoutData();
    }
    @ResponseBody
    @RequestMapping("/remove/role.json")
    public ResultEntity<String> removeRole(@RequestParam("id") Integer id){
        roleService.remove(id);
        return ResultEntity.successWithoutData();
    }
}
