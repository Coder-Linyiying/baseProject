package com.linyy.kitty.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linyy.kitty.admin.constants.SysConstants;
import com.linyy.kitty.admin.entity.SysMenu;
import com.linyy.kitty.admin.mapper.SysMenuMapper;
import com.linyy.kitty.admin.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public int save(SysMenu bean) {
        return 0;
    }

    @Override
    public int update(SysMenu bean) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int delete(List<Long> beans) {
        return 0;
    }

    @Override
    public SysMenu findById(Long id) {
        return null;
    }

    @Override
    public IPage<SysMenu> findPage(Map<String, Object> params) {
        Page<SysMenu> page = new Page<>();
        int current = (params.get("page") == null || Integer.valueOf(params.get("page").toString()) < 0 ) ? 1 : Integer.parseInt(params.get("page").toString());
        long size = (params.get("limit") == null || Long.valueOf(params.get("limit").toString()) < 0 ) ? 1 : Long.parseLong(params.get("limit").toString());
        page.setCurrent(current);
        page.setSize(size);
        return sysMenuMapper.selectPage(page, params);
    }

    @Override
    public List<SysMenu> findAll() {
        return sysMenuMapper.selectAll();
    }

    @Override
    public List<SysMenu> findByUser(String userName) {
        if(userName == null || "".equals(userName) || SysConstants.ADMIN.equalsIgnoreCase(userName)) {
            return sysMenuMapper.selectAll();
        }
        return sysMenuMapper.findByUserName(userName);
    }


}
