package com.mall.common.service;

/**
 * 自定义一个回调函数的方法；来优化RedisService
 * @author Administrator
 *
 */
public interface Function<T,E> {
    public T callback(E e);
}
