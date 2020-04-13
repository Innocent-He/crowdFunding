package com.hgy.crowd.service;

import com.github.pagehelper.PageInfo;
import com.hgy.crowd.entity.Admin;
import com.hgy.crowd.entity.Role;

import java.util.List;

/**
 * @Author He
 * @Date 2020/3/30 10:27
 * @Version 1.0
 */
public interface AdminService {
    void savaAdmin(Admin admin);
    List<Admin> queryAll();

    Admin getAdmintByLoginAcct(String loginAcct, String userPswd);
    PageInfo<Admin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);

    Integer deleteById(Integer id);

    Admin queryAdminById(Integer id);

    void updateAdmin(Admin admin);

    void editAdminAssignRole(Integer id, List<Integer> roleList);
    Admin getAdminByLoginAcct(String username);
}
