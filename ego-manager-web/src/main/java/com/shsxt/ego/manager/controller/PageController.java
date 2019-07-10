package com.shsxt.ego.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {


    /**
     * 页面资源控制器
     *
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String page(@PathVariable  String page){
//        System.out.println("转发页面-->"+page);
        return page;
    }
}
