package com.linyy.kitty.admin.controller;

import com.linyy.kitty.admin.entity.SysUser;
import com.linyy.kitty.admin.redis.RedisUtil;
import com.linyy.kitty.admin.service.SysUserService;
import com.linyy.kitty.admin.utils.HttpResult;
import com.linyy.kitty.admin.utils.PasswordUtils;
import com.linyy.kitty.admin.vo.LoginBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sys")
public class SysLoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("login")
    public HttpResult login(LoginBean loginBean) {
        String username = loginBean.getAccount();
        String password = loginBean.getPassword();
        SysUser user = sysUserService.findByName(username);
        if (null == user) {
            return HttpResult.error("账号不存在");
        }
        if (!match(user, password)) {
            return HttpResult.error("密码不正确");
        }
        if (user.getStatus() == 0) {
            return HttpResult.error("账号已被锁定，请联系管理员");
        }
        redisUtil.set(user.getId().toString(), user, 60);
        return HttpResult.ok(user);
    }

    /**
     * 验证用户密码
     * @param user
     * @param password
     * @return
     */
    public boolean match(SysUser user, String password) {
        return user.getPassword().equals(PasswordUtils.encode(password, user.getSalt()));
    }
}
