package com.hgy.crowd.service.Impl;

import com.hgy.crowd.entity.Menu;
import com.hgy.crowd.entity.MenuExample;
import com.hgy.crowd.mapper.MenuMapper;
import com.hgy.crowd.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author He
 * @Date 2020/4/3 9:39
 * @Version 1.0
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }

    @Override
    public void saveMenu(Menu menu) {
        menuMapper.insertSelective(menu);
    }

    @Override
    public void editMenu(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void removeMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}
