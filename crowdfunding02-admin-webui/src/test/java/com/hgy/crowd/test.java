package com.hgy.crowd;

import com.hgy.crowd.entity.Role;
import com.hgy.crowd.mapper.AdminMapper;
import com.hgy.crowd.mapper.RoleMapper;
import com.hgy.crowd.service.AdminService;

import com.hgy.crowd.util.CrowdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author He
 * @Date 2020/3/29 21:48
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-service.xml"})
public class test {
    @Autowired
    private AdminService adminService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Test
    public void mybatisTest() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
    @Test
    public void Test1(){
        System.out.println(CrowdUtil.md5("111111"));
    }
}
