package com.mall.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mall.web.service.IndexService;

@Controller
@RequestMapping("index")
public class IndexController {

    @Autowired
    private IndexService indexService;
    
    /**
     * 首页
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        //在首页中添加大广告数据
       String indexAd1 = this.indexService.queryIndexAd1();
        mv.addObject("indexAd1",indexAd1);

        //在首页中添加小广告数据
        String indexAd2 = this.indexService.queryIndexAd2();
        mv.addObject("indexAd2",indexAd2);
        return mv;
    }
}
