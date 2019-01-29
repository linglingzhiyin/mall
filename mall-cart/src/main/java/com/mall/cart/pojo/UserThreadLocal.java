package com.mall.cart.pojo;



/**
 * 通过token进行2次查询，浪费了资源
 * 将User对象放置到request对象中，使用Threadlocal实现
 * 进入tomcat和产生响应前，都处在同一个线程
 * 
 * @author Administrator
 *
 */
public class UserThreadLocal {

    private static final ThreadLocal<User> THREAD_LOCAL = new ThreadLocal<>();
    
    public static void set(User user) {
        THREAD_LOCAL.set(user);
    }
    
    public static User get() {
        return THREAD_LOCAL.get();
    }
}
