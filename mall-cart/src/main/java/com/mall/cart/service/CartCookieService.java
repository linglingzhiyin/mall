package com.mall.cart.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.cart.pojo.Cart;
import com.mall.cart.pojo.Item;
import com.mall.common.util.CookieUtils;

@Service
public class CartCookieService {
    
    private static final String COOKIE_NAME="TT_CART";
    
    public static final Integer COOKIE_TIME = 60 * 60 * 24 * 30 * 12;

    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    @Autowired
    private ItemService itemService;
    
    
    /**
     * 查询商品列表 TODO 按照创建时间倒叙排序
     * @param request
     * @return
     */
    public List<Cart> queryCartList(HttpServletRequest request) {
        String cookieValue = CookieUtils.getCookieValue(request, COOKIE_NAME,true);
        if (StringUtils.isEmpty(cookieValue)) {
            return new ArrayList<Cart>(0);
        }
        try {
            return MAPPER.readValue(cookieValue, MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return new ArrayList<Cart>(0);
    }
    
    /**
     * 将商品加入购物车
     * @param itemId
     * @param request
     * @param response
     */
    public void addItemToCart(Long itemId,HttpServletRequest request,HttpServletResponse response) {
        //判断该商品是否在购物车中，如果存在相加，不存在直接添加
        List<Cart> carts = this.queryCartList(request);
        Cart cart = null;
        for (Cart cart2 : carts) {
            if (cart2.getItemId().longValue() == itemId.longValue()) {
                cart = cart2;
                break;
            }
        }
        if (cart == null) {
            //如果不存在
            cart = new Cart();
            cart.setCreated(new Date());
            cart.setUpdated(cart.getCreated());
            cart.setItemId(itemId);
            cart.setNum(1);//TODO
            Item item = this.itemService.queryItemById(itemId);
            cart.setItemTitle(item.getTitle());
            cart.setItemImage(StringUtils.split(item.getImage())[0]);
            cart.setItemPrice(item.getPrice());
            carts.add(cart);
        }else {
            //如果存在
            cart.setNum(cart.getNum() + 1);//TODO
            cart.setUpdated(new Date());
        }
        try {
            CookieUtils.setCookie(request, response, COOKIE_NAME, MAPPER.writeValueAsString(carts));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 更改商品数量
     * @param itemId
     * @param num
     * @param request
     * @param response
     */
    public void updateNum(Long itemId,Integer num,HttpServletRequest request,HttpServletResponse response) {
        List<Cart> carts = this.queryCartList(request);
        for (Cart cart : carts) {
            if (cart.getItemId().longValue() == itemId.longValue()) {
                cart.setNum(num);
                cart.setUpdated(new Date());
                break;
            }
        }
        try {
            CookieUtils.setCookie(request, response, COOKIE_NAME, MAPPER.writeValueAsString(carts));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 删除商品
     * @param itemId
     * @param request
     * @param response
     */
    public void deleteItem(Long itemId, HttpServletRequest request, HttpServletResponse response) {
        List<Cart> carts = this.queryCartList(request);
        for (Cart cart : carts) {
            if (cart.getItemId().longValue() == itemId.longValue()) {
                carts.remove(cart);
                break;
            }
        }
        try {
            CookieUtils.setCookie(request, response, COOKIE_NAME, MAPPER.writeValueAsString(carts));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
