package com.mall.cart.service;

import com.mall.cart.pojo.Item;
import com.mall.common.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ItemService {
    @Autowired
    private ApiService apiService;
    
    
    @Value("${MALL_MANAGE_URL}")
    private String MALL_MANAGE_URL;
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    /**
     * 查询商品基本信息
     * @param itemId
     * @return
     */
    public Item queryItemById(Long itemId) {
        String url = MALL_MANAGE_URL + "/rest/api/item/" + itemId;
        
        String jsonData = null;
        try {
            jsonData = this.apiService.doGet(url);
            
            return MAPPER.readValue(jsonData, Item.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return null;
    }

}
