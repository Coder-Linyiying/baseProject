package com.linyy.system.controller;

import com.linyy.system.service.SysMenuService;
import com.linyy.system.utils.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("list")
    public HttpResult list(@RequestParam Map<String, Object> params) {
        HttpResult result = HttpResult.ok();
        result.setData(sysMenuService.findPage(params));
        return result;
    }
}
