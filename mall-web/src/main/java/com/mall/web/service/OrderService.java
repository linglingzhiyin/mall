package com.mall.web.service;


import com.mall.common.httpclient.HttpResult;
import com.mall.common.service.ApiService;
import com.mall.web.bean.Order;
import com.mall.web.bean.User;
import com.mall.web.bean.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderService {

    @Value("${MALL_ORDER_URL}")
    private String MALL_ORDER_URL;
    
    @Autowired
    private ApiService apiService;
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    /**
     * 提交订单到订单系统
     * 
     * @param order
     * @return
     */
    public String submit(Order order) {
        String url = MALL_ORDER_URL + "/order/create";

        // 填充当前登录用户的信息
        User user = UserThreadLocal.get();
        order.setUserId(user.getId());
        order.setBuyerNick(user.getUsername());
        try {
            String json = MAPPER.writeValueAsString(order);
            HttpResult httpResult = this.apiService.doPostJson(url, json);
            if (httpResult.getCode().intValue() == 200) {
                String body = httpResult.getBody();
                JsonNode jsonNode = MAPPER.readTree(body);
                if (jsonNode.get("status").asInt() == 200) {
                    // 提交成功
                    return jsonNode.get("data").asText();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过主键查询订单
     * @param orderId
     * @return
     */
    public Order queryByOrderId(String orderId) {
        String url = MALL_ORDER_URL + "/order/query/" + orderId;
        try {
            String jsonData = this.apiService.doGet(url);
            if (StringUtils.isNotEmpty(jsonData)) {
                return MAPPER.readValue(jsonData, Order.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
