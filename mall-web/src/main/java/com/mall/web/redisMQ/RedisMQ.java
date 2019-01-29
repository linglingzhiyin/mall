package com.mall.web.redisMQ;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.service.RedisService;
import com.mall.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.IOException;

public class RedisMQ implements MessageListener {


    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        RedisSerializer<?> serializer = redisTemplate.getValueSerializer();
        Object body = serializer.deserialize(message.getBody());
        try {
            JsonNode jsonNode=MAPPER.readTree(String.valueOf(body));
            Long itemId=jsonNode.get("itemId").asLong();
            String key= ItemService.REDIS_KEY_BASE+itemId;
            redisService.del(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
