package com.mall.cart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.cart.pojo.User;
import com.mall.cart.pojo.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mall.cart.pojo.Cart;
import com.mall.cart.service.CartCookieService;
import com.mall.cart.service.CartService;

@Controller
@RequestMapping("cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private CartCookieService cartCookieService;
    
    /**
     * 查询购物车列表
     * @return
     */
    @RequestMapping(value="list", method=RequestMethod.GET)
    public ModelAndView toCartList(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("cart");
        List<Cart> cartList = null;
        User user = UserThreadLocal.get();
        if (null == user) {
            //未登录
            cartList = this.cartCookieService.queryCartList(request);
        }else {
            //登录状态
            cartList = this.cartService.queryCartList();
        }
        mv.addObject(cartList);
        return mv;
    }
    
    /**
     * 将商品添加到购物车之后进行跳转
     * @param itemId
     * @return
     */
    @RequestMapping(value="{itemId}",method=RequestMethod.GET)
    public String addItemToCart(@PathVariable("itemId")Long itemId,HttpServletRequest request,HttpServletResponse response
            ) {
        User user = UserThreadLocal.get();
        if (null == user) {
            //未登录状态
            this.cartCookieService.addItemToCart(itemId, request, response);
        }else {
            //登录状态
            this.cartService.addItemToCart(itemId);
        }
        return "redirect:/cart/list.html";
    }
    
    /**
     * 更新购物车中的商品数量
     * @param itemId
     * @param num
     * @return
     */
    @RequestMapping(value="update/num/{itemId}/{num}",method=RequestMethod.POST)
    public ResponseEntity<Void> updateNum(@PathVariable("itemId")Long itemId,@PathVariable("num")Integer num,HttpServletRequest request,HttpServletResponse response){
        User user = UserThreadLocal.get();
        if (null == user) {
            //未登录状态
            this.cartCookieService.updateNum(itemId, num, request, response);
        }else {
           //登录状态
            this.cartService.updateNum(itemId,num);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    /**
     * 删除
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="delete/{itemId}",method=RequestMethod.GET)
    public String deleteItem(@PathVariable("itemId")Long itemId,HttpServletRequest request,HttpServletResponse response) {
        User user = UserThreadLocal.get();
        if (user == null) {
            //未登录
            this.cartCookieService.deleteItem(itemId, request, response);
        }else {
            //已登录
            this.cartService.deleteItem(itemId);
        }
        return "redirect:/cart/list.html";
    }
}
