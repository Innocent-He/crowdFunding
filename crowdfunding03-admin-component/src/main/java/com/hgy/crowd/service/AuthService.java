package com.hgy.crowd.service;

import com.hgy.crowd.entity.Auth;

import java.util.List;

/**
 * @Author He
 * @Date 2020/4/4 11:29
 * @Version 1.0
 */
public interface AuthService {
    List<Auth> getAllAuth();

    List<Integer> getAuthByRoleId(Integer roleId);
    List<String> getAuthNameByAdminId(Integer adminId);
}
