package com.mall.manage.controller.api;

import com.mall.manage.pojo.ItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mall.manage.service.ItemParamItemService;

@Controller
@RequestMapping("api/item/param/item")
public class ApiItemParamItemController {
    
    @Autowired
    private ItemParamItemService itemParamItemService;
    
    /**
     * 对外提供接口，根据商品id查询商品规格参数数据
     * @param itemId
     * @return
     */
    @RequestMapping(value="{ItemId}",method=RequestMethod.GET)
    public ResponseEntity<ItemParamItem> queryItemDescById(@PathVariable("ItemId")Long itemId) {
        try {
            ItemParamItem record = new ItemParamItem();
            record.setItemId(itemId);
            ItemParamItem itemParamItem = itemParamItemService.queryOne(record);
            if (itemParamItem == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(itemParamItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
