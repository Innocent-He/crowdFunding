package com.hgy.crowd.mvc.handler;

import com.hgy.crowd.entity.Auth;
import com.hgy.crowd.entity.Role;
import com.hgy.crowd.service.AdminService;
import com.hgy.crowd.service.AuthService;
import com.hgy.crowd.service.RoleService;
import com.hgy.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author He
 * @Date 2020/4/4 9:00
 * @Version 1.0
 */
@Controller
public class AssignHandler {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AuthService authService;
    @RequestMapping("/assign/to/page.html")
    public String toAssignPage(@RequestParam("adminId") Integer id,
                               ModelMap map){

        List<Role> assignedRoleList=roleService.getAssignedRole(id);
        List<Role> unAssignedRoleList=roleService.getUnAssignedRole(id);
        map.addAttribute("assignedRoleList",assignedRoleList);
        map.addAttribute("unAssignedRoleList",unAssignedRoleList);
        return "admin-assign-role";
    }
    @RequestMapping("/assign/edit/role.html")
    public String editAdminAssignRole(@RequestParam("adminId") Integer id,
                                      @RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("keyWord") String keyWord,
                                      @RequestParam(value = "assignRoleIdList",required = false) List<Integer> roleList){
        adminService.editAdminAssignRole(id,roleList);
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyWord"+keyWord;
    }
    @ResponseBody
    @RequestMapping("/assign/getAll/auth.json")
    public ResultEntity<List<Auth>> getAllAuth(){
        List<Auth> auths=authService.getAllAuth();
        return ResultEntity.successWithData(auths);
    }
    //通过角色id查询所拥有的权限的id
    @ResponseBody
    @RequestMapping("/get/AuthId.json")
    public ResultEntity<List<Integer>> getAuthId(Integer roleId){
        List<Integer> authIds=authService.getAuthByRoleId(roleId);
        return ResultEntity.successWithData(authIds);
    }
    @ResponseBody
    @RequestMapping("/save/authByRoleId.json")
    public ResultEntity<String> saveAuthByRoleId(@RequestBody Map<String,List<Integer>> map){
        List<Integer> roleId = map.get("roleId");
        List<Integer> authIdList = map.get("authIdList");
        roleService.saveAuthByRoleId(roleId,authIdList);
        return ResultEntity.successWithoutData();
    }

}
