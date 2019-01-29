package com.mall.manage.controller;

import com.mall.common.bean.EasyUIResult;
import com.mall.manage.pojo.ItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.manage.service.ItemParamService;

@Controller
@RequestMapping("item/param")
public class ItemParamController {
    
    @Autowired
    private ItemParamService itemParamService;
    
    /**
     * 根据商品类目id查找规格参数模板
     * @param itemCatId
     * @return
     */
    @RequestMapping(value="{itemCatId}",method=RequestMethod.GET)
    public ResponseEntity<ItemParam> queryItemParamByItemCatId(@PathVariable("itemCatId")Long itemCatId) {
        try {
            ItemParam record = new ItemParam();
            record.setItemCatId(itemCatId);
            ItemParam itemParam = itemParamService.queryOne(record);
            if (null == itemParam) {
                //404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(itemParam);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    
    /**
     * 新增规格参数模板
     * @param itemCatId
     * @param paramData
     * @return
     */
    @RequestMapping(value="{itemCatId}",method=RequestMethod.POST)
    public ResponseEntity<Void> saveItemParam(@PathVariable("itemCatId")Long itemCatId,@RequestParam("paramData")String paramData) {
        try {
            ItemParam record = new ItemParam();
            record.setParamData(paramData);
            record.setId(null);
            record.setItemCatId(itemCatId);
            this.itemParamService.save(record);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @RequestMapping(value="list",method = RequestMethod.GET)
    public ResponseEntity<EasyUIResult> queruItemParamList(@RequestParam(value="page"
            ,defaultValue="1")Integer pageNum
            , @RequestParam(value="rows",defaultValue="30")Integer pageSize) {
        try {
            EasyUIResult easyUIResult = this.itemParamService.queryItemParamList(pageNum,pageSize);
            return ResponseEntity.ok(easyUIResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}
