package com.linyy.kitty.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Sets;
import com.linyy.kitty.admin.entity.SysMenu;
import com.linyy.kitty.admin.entity.SysUser;
import com.linyy.kitty.admin.mapper.SysUserMapper;
import com.linyy.kitty.admin.service.SysMenuService;
import com.linyy.kitty.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public int save(SysUser bean) {
        return sysUserMapper.insertSelective(bean);
    }

    @Override
    public int update(SysUser bean) {
        return sysUserMapper.updateByPrimaryKeySelective(bean);
    }

    @Override
    public int delete(Long id) {
        return sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(List<Long> ids) {
        return sysUserMapper.deleteByBatch(ids);
    }

    @Override
    public SysUser findById(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public IPage<SysUser> findPage(Map<String, Object> params) {
        Page<SysUser> page = new Page<>();
        page.setCurrent(2);
        page.setSize(5);
        return sysUserMapper.selectAll(page);
    }

    @Override
    public Set<String> findPermissions(String userName) {
        Set<String> perms = Sets.newHashSet();
        List<SysMenu> sysMenus = sysMenuService.findByUser(userName);
        for(SysMenu sysMenu:sysMenus) {
            if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())) {
                perms.add(sysMenu.getPerms());
            }
        }
        return perms;
    }

    @Override
    public SysUser findByName(String name) {
        return sysUserMapper.findByName(name);
    }
}
