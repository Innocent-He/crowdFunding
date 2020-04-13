package com.hgy.crowd.mvc.config;

import com.hgy.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 *考虑到User对象中仅仅包含账号和密码，为了能够获取到原始的Admin对象，专门创建这个类对User对象进行扩展
 * @Author He
 * @Date 2020/4/6 12:59
 * @Version 1.0
 */
public class SecurityAdmin extends User {
    private static final long serialVersionUID=1L;
    private Admin originalAdmin;
    public SecurityAdmin(Admin originalAdmin, List<GrantedAuthority> authorities) {
        super(originalAdmin.getLoginAcct(), originalAdmin.getUserPswd(), authorities);
        this.originalAdmin=originalAdmin;
    }

    public Admin getOriginalAdmin() {
        return originalAdmin;
    }
}
