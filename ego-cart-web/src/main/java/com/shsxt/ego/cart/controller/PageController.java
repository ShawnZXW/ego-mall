package com.shsxt.ego.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 购物车资源控制器
 *
 * @author Shawn
 * @date 2019-07-08 23:01
 */

@Controller
public class PageController {


    @RequestMapping("/{page}")
    public String page(@PathVariable String page){
        System.out.println("测试页面。。。");
        return page;
    }
}
