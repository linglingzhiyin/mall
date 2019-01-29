package com.mall.web.controller;


import com.mall.manage.pojo.Item;
import com.mall.manage.pojo.ItemDesc;
import com.mall.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 显示商品数据
     *
     * @param itemId
     * @return
     */
    @RequestMapping(value = "{itemId}", method = RequestMethod.GET)
    public ModelAndView showDetail(@PathVariable("itemId") Long itemId) {
        ModelAndView mv = new ModelAndView("item");

        //商品的基本数据
       Item item = this.itemService.queryItemById(itemId);
        mv.addObject("item", item);

         //商品描述数据
        ItemDesc itemDesc = this.itemService.queryItemDescByItemId(itemId);
        mv.addObject("itemDesc", itemDesc);

        //商品规格参数数据
       String html = this.itemService.queryItemParamItemByItemId(itemId);
        mv.addObject("itemParam", html);

      return mv;
    }

}
