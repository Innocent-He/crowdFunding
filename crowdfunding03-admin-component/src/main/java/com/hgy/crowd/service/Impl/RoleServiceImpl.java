package com.hgy.crowd.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hgy.crowd.entity.Role;
import com.hgy.crowd.mapper.RoleMapper;
import com.hgy.crowd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author He
 * @Date 2020/4/2 8:48
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> roles = roleMapper.selectRoleByKeyword(keyword);
        return new PageInfo<>(roles);
    }

    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public Role queryRoleById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void remove(Integer id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Role> getAssignedRole(Integer adminId) {
        return roleMapper.selectAssignedRole(adminId);
    }

    @Override
    public List<Role> getUnAssignedRole(Integer adminId) {
        return roleMapper.selectUnAssignedRole(adminId);
    }

    @Override
    public void saveAuthByRoleId(List<Integer> roleId, List<Integer> authIdList) {
        roleMapper.deleteOldAuthByRoleId(roleId.get(0));
        if (authIdList!=null&&authIdList.size()>0) {
            roleMapper.saveAuthByRoleId(roleId.get(0), authIdList);
        }
    }
}
