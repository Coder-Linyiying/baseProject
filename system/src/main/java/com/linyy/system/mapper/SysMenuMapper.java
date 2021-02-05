package com.linyy.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linyy.system.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysMenuMapper {

    Page<SysMenu> selectPage(Page<SysMenu> page, @Param("params") Map<String, Object> params);

    List<SysMenu> selectAll();

    List<SysMenu> findByUserName(String userName);
}