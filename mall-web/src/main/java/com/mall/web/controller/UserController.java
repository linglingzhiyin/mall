package com.mall.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.common.service.RedisService;
import com.mall.common.util.CookieUtils;
import com.mall.web.bean.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("user")
public class UserController {
    
    private static final String  COOKIE_NAME="COOKIE_TOKEN";
    
    @Autowired
    private RedisService redisService;
    
    @RequestMapping(value="logout",method=RequestMethod.GET)
    public String logout(HttpServletRequest request,HttpServletResponse response) {
        UserThreadLocal.set(null);
        String token = CookieUtils.getCookieValue(request, COOKIE_NAME);
        String key="TOKEN_"+token;
        this.redisService.del(key);
        CookieUtils.deleteCookie(request, response, COOKIE_NAME);
        return "redirect:/";
    }
    
}
