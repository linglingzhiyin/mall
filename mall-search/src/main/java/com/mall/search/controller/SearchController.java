package com.mall.search.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mall.search.bean.Item;
import com.mall.search.bean.SearchResult;
import com.mall.search.service.ItemSearchService;;

@Controller
@RequestMapping("search")
public class SearchController {
    
    public static final Integer ROWS = 30;
    
    @Autowired
    private ItemSearchService itemSearchService;

    /**
     * 搜索商品
     * @param keyWords
     * @param page
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView search(@RequestParam("q") String keyWords,@RequestParam(value="page",defaultValue="1") Integer page) {
        ModelAndView mv = new ModelAndView("search");
        SearchResult searchResult=null;
        try {
            keyWords = new String(keyWords.getBytes("ISO-8859-1"), "UTF-8");
            searchResult = this.itemSearchService.search(keyWords,page,ROWS);
        } catch (Exception e) {
            e.printStackTrace();
            searchResult = new SearchResult(0L,new ArrayList<Item>(0));
        }
        //搜索关键字
        mv.addObject("query",keyWords);
        //搜索的结果数据
        mv.addObject("itemList", searchResult.getList());
        
        //当前页数
        mv.addObject("page", page);
        
        int total = searchResult.getTotal().intValue();
        int pages = total%ROWS == 0? total/ROWS : total/ROWS+1;
        
        //总页数
        mv.addObject("pages", pages);
        return mv;
    }
    
}
