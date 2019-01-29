package com.mall.web.service;

import java.util.List;

import com.mall.common.service.ApiService;
import com.mall.web.bean.Cart;
import com.mall.web.bean.User;
import com.mall.web.bean.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class CartService {

    @Autowired
    private ApiService apiService;

    @Value("${MALL_CART_URL}")
    private String MALL_CART_URL;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public List<Cart> queryCartList() {
        try {
            User user = UserThreadLocal.get();
            String url = MALL_CART_URL + "/service/api/cart/" + user.getId();
            String jsonData = this.apiService.doGet(url);
            if (StringUtils.isNotEmpty(jsonData)) {
                return MAPPER.readValue(jsonData,
                        MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
