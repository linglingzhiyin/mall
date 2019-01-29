package com.mall.sso.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.mall.common.util.CookieUtils;
import com.mall.sso.pojo.User;
import com.mall.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.mall.common.util.CookieUtils;

@Controller
@RequestMapping("user")
public class UserController {

    private static final String COOKIE_NAME="COOKIE_TOKEN";
    
    @Autowired
    private UserService userService;
    
    
    @RequestMapping(value="register",method=RequestMethod.GET)
    public String register() {
        return "register";
    }
    
    /**
     * 检查输入的信息是否可用
     * @param param
     * @param type
     * @return
     */
    @RequestMapping(value="check/{param}/{type}",method = RequestMethod.GET)
    public ResponseEntity<Boolean> check(@PathVariable("param")String param,@PathVariable("type")Integer type) {
        try {
            Boolean bool = this.userService.check(param, type);
            if (null == bool) {
                //参数有误:400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            //为了符合前端逻辑
            return ResponseEntity.ok(!bool);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    
    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping(value="doRegister",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> doRegister(@Valid User user, BindingResult bindingResult) {
        try {
            Map<String, Object> result = new HashMap<>();
            if (bindingResult.hasErrors()) {
                //校验错误
                List<String> msgs = new ArrayList<>();
                List<ObjectError> allErrors = bindingResult.getAllErrors();
                for (ObjectError objectError : allErrors) {
                    msgs.add(objectError.getDefaultMessage());
                }
                result.put("status", "400");
                result.put("data", StringUtils.join(msgs,'|'));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }
            Boolean bool = this.userService.doRegister(user);
            if (bool) {
                result.put("status", "200");
                return ResponseEntity.ok(result);
            }
            result.put("status", "500");
            result.put("data", "2333");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping(value="login",method=RequestMethod.GET)
    public String toLogin() {
        return "login";
    }
    
    
    /**
     * 登录
     * 
     * @param user
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="doLogin",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doLogin(User user,HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        try {
            String token = this.userService.doLogin(user.getUsername(), user.getPassword());
            if (null == token) {
                result.put("status", 400);
            }else {
                result.put("status", 200);
               CookieUtils.setCookie(request, response, COOKIE_NAME, token);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", 500);
        }
        return result;
    }
    
    /**
     * 根据token查询用户信息(禁用，在dubbo中去调用)
     * @param token
     * @return
     */
    @RequestMapping(value = "{token}",method=RequestMethod.GET)
    public ResponseEntity<User> queryByToken(@PathVariable("token")String token) {
        User user=null;
        try {
            user = this.userService.queryUserByToken(token);
            if (null == user) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            System.out.println(user.toString());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        User user = new  User();
//        user.setUsername("此服务没有了，请访问ssoquery.mall.com或dubbo中的服务");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
    }
    
}
