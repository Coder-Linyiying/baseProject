package com.linyy.system.service;

import com.linyy.system.entity.SysMenu;

import java.util.List;

public interface SysMenuService extends CrudService<SysMenu, Long> {

    List<SysMenu> findAll();

    List<SysMenu> findByUser(String userName);
}
