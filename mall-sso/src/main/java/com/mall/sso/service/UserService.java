package com.mall.sso.service;


import com.mall.sso.pojo.User;

public interface UserService {

    /**
     * 检查数据是否可用
     * @param param 传入的具体数据
     * @param type 传入的数据类型是用户名还是手机号还是邮箱类型
     * @return
     */
    public Boolean check(String param, Integer type);
    
    /**
     * 注册时检查用户是否存在
     * @param user
     * @return
     */
    public Boolean doRegister(User user);
    
    /**
     * 登录
     * @param username
     * @param password
     * @return
     * @throws Exception 
     */
    public String doLogin(String username, String password) throws Exception ;

       
    /**
     * 单点登录的token
     * @param token
     * @return
     */
    public User queryUserByToken(String token);
        
}
