package com.mall.cart.controller.api;

import java.util.List;

import com.mall.cart.pojo.Cart;
import com.mall.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 对外开放的接口
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("api/cart")
public class ApiCartController {
    
    @Autowired
    private CartService cartService;

    /**
     * 根据用户id查询购物车列表
     * @param userId
     * @return
     */
    @RequestMapping(value = "{userId}",method = RequestMethod.GET)
    public ResponseEntity<List<Cart>> queryCartListByUserId(@PathVariable("userId") Long userId){
        try {
            List<Cart> cartList = this.cartService.queryCartList(userId);
            if (null == cartList || cartList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(cartList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
}
