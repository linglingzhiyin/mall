package com.mall.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.common.util.CookieUtils;
import com.mall.web.bean.User;
import com.mall.web.bean.UserThreadLocal;
import com.mall.web.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class UserLoginHandlerInterceptor implements HandlerInterceptor{
    
    public static final String COOKIE_NAME = "COOKIE_TOKEN";

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = CookieUtils.getCookieValue(request, COOKIE_NAME);
        if (StringUtils.isEmpty(token)) {
            //未登录，跳转到登录页面
            response.sendRedirect(this.userService.MALL_SSO_URL+"/user/login.html");
            return false;
        }
        User user = this.userService.queryByToken(token);
        if (user == null) {
            //存在cookie但是redis中不存在值，表明登录超时
            response.sendRedirect(this.userService.MALL_SSO_URL+"/user/login.html");
            return false;
        }
        //登录成功
        UserThreadLocal.set(user);//将对象写入线程
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
        // 此方法在视图渲染之后执行
        UserThreadLocal.set(null);//必写，清空操作
        
    }

    
    
}
