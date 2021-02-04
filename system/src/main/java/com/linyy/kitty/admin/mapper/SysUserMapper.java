package com.linyy.kitty.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linyy.kitty.admin.entity.SysUser;
import java.util.List;

public interface SysUserMapper {

    IPage<SysUser> selectAll(Page<SysUser> page);

    SysUser selectByPrimaryKey(Long id);

    int insertSelective(SysUser sysUser);

    int updateByPrimaryKeySelective(SysUser sysUser);

    int deleteByPrimaryKey(Long id);

    int deleteByBatch(List<Long> ids);

    SysUser findByName(String name);
}