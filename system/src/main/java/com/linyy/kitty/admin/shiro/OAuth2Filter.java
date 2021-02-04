package com.linyy.kitty.admin.shiro;

import com.alibaba.fastjson.JSONObject;
import com.linyy.kitty.admin.utils.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuth2Filter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //获取请求的token
        String token = getRequestToken((HttpServletRequest) servletRequest);
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return new OAuth2Token(token);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if ("OPTIONS".equals(request.getMethod())) {
            //如果是跨域请求中复杂请求的预检请求（OPTIONS类型），因为预检请求不带token，所以不需要验证token
            return true;
        }
        //获取请求token，如果token不存在，直接返回401
        String token = getRequestToken(request);
        if (StringUtils.isBlank(token)) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpResult result = HttpResult.error(HttpStatus.UNAUTHORIZED.value(), "invalid token");
            String json = JSONObject.toJSONString(result);
            response.getWriter().println(json);
            return false;
        }
        return executeLogin(servletRequest, servletResponse);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try {
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            HttpResult result = HttpResult.error(HttpStatus.UNAUTHORIZED.value(), throwable.getMessage());
            String json = JSONObject.toJSONString(result);
            httpServletResponse.getWriter().println(json);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    /**
     * 获取请求token
     * @param request
     * @return
     */
    private String getRequestToken(HttpServletRequest request) {
        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }
        return token;
    }
}
