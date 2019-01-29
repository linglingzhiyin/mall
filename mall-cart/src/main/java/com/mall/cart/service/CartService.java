package com.mall.cart.service;

import java.util.Date;
import java.util.List;

import com.mall.cart.mapper.CartMapper;
import com.mall.cart.pojo.Cart;
import com.mall.cart.pojo.Item;
import com.mall.cart.pojo.User;
import com.mall.cart.pojo.UserThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;
    
    @Autowired
    private ItemService itemService;
    
    /**
     * 将商品放入购物车
     * @param itemId
     */
    public void addItemToCart(Long itemId) {
        //判断该商品在购物车中是否存在
        User user = UserThreadLocal.get();
        Cart record = new Cart();
        record.setItemId(itemId);
        record.setUserId(user.getId());
        Cart cart = this.cartMapper.selectOne(record);
        if (null == cart) {
            //购物车中不存在该商品
            cart = new Cart();
            cart.setItemId(itemId);
            cart.setUserId(user.getId());
            cart.setNum(1);//TODO 先默认为1
            cart.setCreated(new Date());
            cart.setUpdated(cart.getCreated());
            
            Item item = this.itemService.queryItemById(itemId);
            cart.setItemImage(StringUtils.split(item.getImage(), ",")[0]);
            cart.setItemPrice(item.getPrice());
            cart.setItemTitle(item.getTitle());
            
            //将Cart保存到数据库中
            this.cartMapper.insert(cart);
            
        }else {
            //该商品已经在购物车中
            cart.setNum(cart.getNum()+1);//TODO 默认为1
            cart.setUpdated(new Date());
            this.cartMapper.updateByPrimaryKey(cart);
        }
    }

    /**
     * 查询购物车列表
     * @return
     */
    public List<Cart> queryCartList() {
        Example example = new Example(Cart.class);
        example.setOrderByClause("created DESC");
        //TODO 分页查询
        User user = UserThreadLocal.get();
        example.createCriteria().andEqualTo("userId", user.getId());
        return this.cartMapper.selectByExample(example);
    }
    
    /**
     * 查询购物车列表
     * @return
     */
    public List<Cart> queryCartList(Long userId) {
        Example example = new Example(Cart.class);
        example.setOrderByClause("created DESC");
        //TODO 分页查询
        example.createCriteria().andEqualTo("userId", userId);
        return this.cartMapper.selectByExample(example);
    }
    

    /**
     * 更新数量
     * @param itemId
     * @param num
     */
    public void updateNum(Long itemId, Integer num) {
        //更新套件
        Example example = new Example(Cart.class);
        example.createCriteria().andEqualTo("userId", UserThreadLocal.get().getId()).andEqualTo("itemId", itemId);
        //更新内容
        Cart record = new Cart();
        record.setNum(num);
        record.setUpdated(new Date());
        //执行更新
        this.cartMapper.updateByExampleSelective(record, example);
        
    }

    /**
     * 删除
     * @param itemId
     */
    public void deleteItem(Long itemId) {
        //实现物理删除
        Cart record = new Cart();
        record.setItemId(itemId);
        record.setUserId(UserThreadLocal.get().getId());
        this.cartMapper.delete(record);
    }

}
