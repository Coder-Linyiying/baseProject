package com.linyy.system.service;

import com.linyy.system.entity.SysUser;

import java.util.Set;

public interface SysUserService extends CrudService<SysUser, Long> {

    Set<String> findPermissions(String userName);

    SysUser findByName(String name);

}
