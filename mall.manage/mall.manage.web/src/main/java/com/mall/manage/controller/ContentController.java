package com.mall.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.common.bean.EasyUIResult;
import com.mall.manage.pojo.Content;
import com.mall.manage.service.ContentService;

@Controller
@RequestMapping("content")
public class ContentController {
    
    @Autowired
    private ContentService contentService;

    /**
     * 新增内容管理
     * @param content
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveContent(Content content) {
        try {
            content.setId(null);
            this.contentService.save(content);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    /**
     * 按照分类id查询该分类下的所有内容列表，并且按照更新时间倒叙排序
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<EasyUIResult> queryListByCategoryId(@RequestParam("categoryId")Long categoryId,@RequestParam("page")Integer pageNum,@RequestParam("rows")Integer pageSize) {
        try {
            EasyUIResult easyUIResult = this.contentService.queryPageListByCategoryId(categoryId, pageNum, pageSize);
            return ResponseEntity.ok(easyUIResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
