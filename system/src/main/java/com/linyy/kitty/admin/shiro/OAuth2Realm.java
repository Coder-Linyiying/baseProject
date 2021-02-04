package com.linyy.kitty.admin.shiro;

import com.alibaba.fastjson.JSONObject;
import com.linyy.kitty.admin.entity.SysUser;
import com.linyy.kitty.admin.redis.RedisUtil;
import com.linyy.kitty.admin.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(接口保护，验证接口调用权限时调用)
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        //用户权限列表，根据用户拥有的权限标识与如@permission标注的接口对比，决定是否可以调用接口
        Set<String> permissions = sysUserService.findPermissions(user.getName());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 认证（登录时调用）
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        //根据accessToken,查询用户token信息
        Map<String, Object> userToken = (Map<String, Object>) redisUtil.get(token);
        if (userToken == null) {
            //token失效
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //查询用户信息
        SysUser user = sysUserService.findById(Long.valueOf(userToken.get("id").toString()));
        //账号被锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定，请联系管理员");
        }
        SimpleAuthenticationInfo  info = new SimpleAuthenticationInfo (user, token, getName());
        return info;
    }
}
