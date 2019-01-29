package com.mall.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AbcController {


    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "products/{abc}", method = RequestMethod.GET)
    public String index(@PathVariable("abc") Long itemId) {
        return "redirect:/index.html";
    }

    @RequestMapping(value = "{abc}", method = RequestMethod.GET)
    public String index1(@PathVariable("abc") String itemId) {
        return "redirect:/index.html";
    }
}
