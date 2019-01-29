package com.mall.manage.controller.api;

import com.mall.common.bean.EasyUIResult;
import com.mall.manage.pojo.Content;
import com.mall.manage.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("api/content")
public class ApiContentController {
    
    @Autowired
    private ContentService contentService;

    /**
     * 按照分类id查询该分类下的所有内容列表，并且按照更新时间倒叙排序
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<EasyUIResult> queryListByCategoryId(
            @RequestParam("categoryId")Long categoryId,@RequestParam("page")Integer pageNum,@RequestParam("rows")Integer pageSize) {
        try {
            EasyUIResult easyUIResult = this.contentService.queryPageListByCategoryId(categoryId, pageNum, pageSize);
            return ResponseEntity.ok(easyUIResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
