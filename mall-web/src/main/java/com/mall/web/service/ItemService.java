package com.mall.web.service;

import com.mall.common.service.ApiService;
import com.mall.common.service.RedisService;
import com.mall.manage.pojo.ItemDesc;
import com.mall.web.bean.Item;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@Service
public class ItemService {
    @Autowired
    private ApiService apiService;
    
    @Autowired
    private RedisService redisService;
    
    @Value("${MALL_MANAGE_URL}")
    private String MALL_MANAGE_URL;
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    public static final String REDIS_KEY_BASE = "MALL_WEB_ITEM_BASE_";//商品基本信息字符串凭借
    
    private static final Integer REDIS_TIME = 60*60*24*30*3;
    
    
    /**
     * 查询商品基本信息
     * @param itemId
     * @return
     */
    public Item queryItemById(Long itemId) {
        String url = MALL_MANAGE_URL + "/rest/api/item/" + itemId;
        String REDIS_KEY = REDIS_KEY_BASE+itemId;
        
        //先从缓存中命中
        try {
            String cacheData = this.redisService.get(REDIS_KEY);
            //判断命中的缓存是否为空
            if (StringUtils.isNoneEmpty(cacheData)) {
                //命中，返回,将转化成json存储在redis中的数据读出来
                return MAPPER.readValue(cacheData, Item.class);
                
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        
        
        String jsonData = null;
        try {
            jsonData = this.apiService.doGet(url);
            
            //在返回数据之前将数据写入缓存中
            //此处不使用jdk自带的序列化方式将结果介入，而是使用jackson的方法将结果转化成json数据保存
            this.redisService.set(REDIS_KEY,jsonData,REDIS_TIME);
            
            //对json数据进行反序列化
            return MAPPER.readValue(jsonData, Item.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return null;
    }

    /**
     * 查询商品详情信息
     * @param itemId
     * @return
     */
    public ItemDesc queryItemDescByItemId(Long itemId) {
        try {
            String url = MALL_MANAGE_URL + "/rest/api/item/desc/" + itemId;
            String jsonData = this.apiService.doGet(url);
            return MAPPER.readValue(jsonData, ItemDesc.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询商品参数信息
     * @param itemId
     * @return
     */
    public String queryItemParamItemByItemId(Long itemId) {
        try {
            String url = MALL_MANAGE_URL + "/rest/api/item/param/item/" + itemId;
            String jsonData = this.apiService.doGet(url);
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            String paramData = jsonNode.get("paramData").asText();
            ArrayNode arrayNode  = (ArrayNode) MAPPER.readTree(paramData);
            StringBuilder sb = new StringBuilder();
            sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\"><tbody>");
            for (JsonNode param : arrayNode) {
                sb.append("<tr><th class=\"tdTitle\" colspan=\"2\">" + param.get("group").asText()
                        + "</th></tr>");
                ArrayNode params = (ArrayNode) param.get("params");
                for (JsonNode p : params) {
                    sb.append("<tr><td class=\"tdTitle\">" + p.get("k").asText() + "</td><td>"
                            + p.get("v").asText() + "</td></tr>");
                }
            }
            sb.append("</tbody></table>");
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
