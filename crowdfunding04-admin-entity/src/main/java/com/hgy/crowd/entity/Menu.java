package com.hgy.crowd.entity;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    //主键
    private Integer id;
    //父节点
    private Integer pid;
    //菜单名称
    private String name;
    //菜单跳转地址
    private String url;
    //菜单图表
    private String icon;
    //存储子节点集合
    private List<Menu> children=new ArrayList<Menu>();
    //菜单默认展开
    private boolean open=true;

    public Menu() {
    }

    public Menu(Integer id, Integer pid, String name, String url, String icon, List<Menu> children, boolean open) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.children = children;
        this.open = open;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                ", open=" + open +
                '}';
    }
}