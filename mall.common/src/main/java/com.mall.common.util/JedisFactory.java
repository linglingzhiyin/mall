package com.mall.common.util;

import org.springframework.beans.factory.FactoryBean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisFactory implements FactoryBean<Jedis> {

    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public Jedis getObject() throws Exception {
        return jedisPool.getResource();
    }

    @Override
    public Class<?> getObjectType() {
        return jedisPool.getResource().getClass();
    }
}
