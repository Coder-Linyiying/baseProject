package com.linyy.kitty.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.linyy.kitty.admin.entity.SysMenu;

import java.util.List;
import java.util.Map;

public interface SysMenuService extends CrudService<SysMenu, Long>{

    List<SysMenu> findAll();

    List<SysMenu> findByUser(String userName);
}
