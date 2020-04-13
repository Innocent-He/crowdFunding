package com.hgy.crowd.mapper;

import com.hgy.crowd.entity.Admin;
import com.hgy.crowd.entity.AdminExample;
import java.util.List;

import com.hgy.crowd.entity.Role;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    List<Admin> selectAdminByKeyWord(String keyword);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    void clearOldRoles(Integer id);

    void saveNewRoles(@Param("adminId") Integer id, @Param("roleList") List<Integer> roleList);
}