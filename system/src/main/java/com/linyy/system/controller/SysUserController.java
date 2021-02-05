package com.linyy.system.controller;

import com.linyy.system.service.SysUserService;
import com.linyy.system.utils.HttpResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value = "用户接口")
@RestController
@RequestMapping("sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    @GetMapping("list")
    public HttpResult list(@RequestParam Map<String, Object> params) {
        HttpResult result = HttpResult.ok();
        result.setData(sysUserService.findPage(params));
        return result;
    }

    @GetMapping("findById")
    public HttpResult findById(@RequestParam Long id) {
        HttpResult result = HttpResult.ok();
        result.setData(sysUserService.findById(id));
        return result;
    }
}
