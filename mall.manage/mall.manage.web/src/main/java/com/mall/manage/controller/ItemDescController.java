package com.mall.manage.controller;

import com.mall.manage.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mall.manage.service.ItemDescService;

@Controller
@RequestMapping("item/desc")
public class ItemDescController {

    @Autowired
    private ItemDescService itemDescService;
    
    /**
     * 通过主键查询商品描述
     * @param ItemId
     * @return
     */
    @RequestMapping(value="{ItemId}",method=RequestMethod.GET)
    public ResponseEntity<ItemDesc> queryItemDescById(@PathVariable("ItemId")Long ItemId) {
        try {
            ItemDesc itemDesc = itemDescService.queryById(ItemId);
            if (itemDesc == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(itemDesc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
}
