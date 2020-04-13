package com.hgy.crowd.service.Impl;

import com.hgy.crowd.entity.Auth;
import com.hgy.crowd.entity.AuthExample;
import com.hgy.crowd.mapper.AuthMapper;
import com.hgy.crowd.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author He
 * @Date 2020/4/4 11:29
 * @Version 1.0
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthMapper authMapper;

    @Override
    public List<Auth> getAllAuth() {
        return authMapper.selectByExample(new AuthExample());
    }

    @Override
    public List<Integer> getAuthByRoleId(Integer roleId) {
        return authMapper.getAuthByRoleId(roleId);
    }
    @Override
    public List<String> getAuthNameByAdminId(Integer adminId){
        return authMapper.selectAuthNameByAdminId(adminId);
    }
}
