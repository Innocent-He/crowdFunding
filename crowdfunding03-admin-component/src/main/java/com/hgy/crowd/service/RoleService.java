package com.hgy.crowd.service;

import com.github.pagehelper.PageInfo;
import com.hgy.crowd.entity.Role;

import java.io.InputStream;
import java.util.List;

/**
 * @Author He
 * @Date 2020/4/2 8:47
 * @Version 1.0
 */
public interface RoleService {
    PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);
    void saveRole(Role role);

    Role queryRoleById(Integer id);

    void updateRole(Role role);

    void remove(Integer id);

    List<Role> getAssignedRole(Integer id);

    List<Role> getUnAssignedRole(Integer id);

    void saveAuthByRoleId(List<Integer> integer, List<Integer> authIdList);
}
