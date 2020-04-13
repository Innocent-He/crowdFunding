package com.hgy.crowd.service;

import com.hgy.crowd.entity.Menu;

import java.util.List;

/**
 * @Author He
 * @Date 2020/4/3 9:39
 * @Version 1.0
 */
public interface MenuService {

    List<Menu> getAll();

    void saveMenu(Menu menu);

    void editMenu(Menu menu);

    void removeMenu(Integer id);
}
