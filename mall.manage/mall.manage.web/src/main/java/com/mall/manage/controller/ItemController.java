package com.mall.manage.controller;

import com.mall.common.bean.EasyUIResult;
import com.mall.manage.pojo.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.manage.service.ItemService;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;
    
    private Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
    
    /**
     * 新增商品
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> save(Item item, @RequestParam("desc") String desc
            , @RequestParam("itemParams") String itemParams) {
        try {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("新增商品：item={}；desc={}",item,desc);
            }
            Boolean bool = itemService.saveItem(item,desc,itemParams);//此处处于事务问题将两个事务合并成一个事务，从日志中可以查看是否为一个事务
            if (!bool) {
                if(LOGGER.isInfoEnabled()) {
                    LOGGER.info("新增商品失败：item={}",item);
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(LOGGER.isInfoEnabled()) {
                LOGGER.info("新增商品成功：itemId={}",item.getId());
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            LOGGER.error("新增商品出错：item={}",item,e);
        }
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 按时间倒叙查询商品列表
     * @param pageNum
     * @param pageSize
     * @return
     */

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<EasyUIResult> queruItemList(@RequestParam(value="page"
            ,defaultValue="1")Integer pageNum
            , @RequestParam(value="rows",defaultValue="30")Integer pageSize) {
        try {
            EasyUIResult easyUIResult = this.itemService.queryItemList(pageNum,pageSize);
            return ResponseEntity.ok(easyUIResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    

    /**
     * 编辑商品
     * @param item
     * @param desc
     * @param itemParams
     * @return
     */

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateItem(Item item,@RequestParam("desc") String desc, @RequestParam("itemParams") String itemParams) {
        try {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("编辑商品：item={}；desc={}",item,desc);
            }
            Boolean bool = itemService.updateItem(item,desc,itemParams);
            if (!bool) {
                if(LOGGER.isInfoEnabled()) {
                    LOGGER.info("编辑商品失败：item={}",item);
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if(LOGGER.isInfoEnabled()) {
                LOGGER.info("编辑商品成功：itemId={}",item.getId());
            }
            //204
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            LOGGER.error("编辑商品出错：item={}",item,e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
