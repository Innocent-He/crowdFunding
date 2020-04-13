package com.hgy.crowd.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hgy.crowd.constant.CrowdConstant;
import com.hgy.crowd.entity.Admin;
import com.hgy.crowd.entity.AdminExample;
import com.hgy.crowd.entity.Role;
import com.hgy.crowd.exception.LoginAcctAlreadyInUserException;
import com.hgy.crowd.exception.LoginFailedException;
import com.hgy.crowd.exception.NoStringException;
import com.hgy.crowd.mapper.AdminMapper;
import com.hgy.crowd.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author He
 * @Date 2020/3/30 10:10
 * @Version 1.0
 */
@Service
public class AdminServiceImpl implements com.hgy.crowd.service.AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void savaAdmin(Admin admin) {
        String psd = CrowdUtil.md5(admin.getUserPswd());
        admin.setUserPswd(psd);
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        admin.setCreateTime(df.format(date));
        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUserException("当前账户已存在");
            }
        }
    }

    @Override
    public List<Admin> queryAll() {
        return adminMapper.selectByExample(new AdminExample());
    }


    /**
     * 验证登录账号密码是否正确
     *
     * @param loginAcct
     * @param userPswd
     * @return
     */
    @Override
    public Admin getAdmintByLoginAcct(String loginAcct, String userPswd) {
        //根据用户名在数据库查询Admin对象
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        //如果查询不到则抛出异常
        if (admins == null || admins.size() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        if (admins.size() > 1) {
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        Admin admin = admins.get(0);
        if (admin == null) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        //如果查询到，则取出对象的密码与加密过后的userPswd作比较
        if (Objects.equals(admin.getUserPswd(), CrowdUtil.md5(userPswd))) {
            //如果密码一致，则返回admin对象
            return admin;
        }
        //如果密码不一致，则抛出异常
        throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);

    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> admins = adminMapper.selectAdminByKeyWord(keyword);
        return new PageInfo<>(admins);
    }

    @Override
    public Integer deleteById(Integer id) {
        int i = adminMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public Admin queryAdminById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public void editAdminAssignRole(Integer id, List<Integer> roleList) {
        adminMapper.clearOldRoles(id);
        if (roleList.size() > 0 && roleList != null) {
            adminMapper.saveNewRoles(id, roleList);
        }
    }

    @Override
    public Admin getAdminByLoginAcct(String username) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andLoginAcctEqualTo(username);
        List<Admin> list = adminMapper.selectByExample(example);
        Admin admin = list.get(0);
        return admin;
    }
}
