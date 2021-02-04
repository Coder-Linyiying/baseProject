package com.linyy.kitty.admin.service;

import com.linyy.kitty.admin.entity.SysUser;

import java.util.Set;

public interface SysUserService extends CrudService<SysUser, Long>{

    Set<String> findPermissions(String userName);

    SysUser findByName(String name);

}
