package com.shsxt.ego.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 视图控制器
 * - 控制 url 资源路径
 */
@Controller
public class PageController {

    @RequestMapping("/{page}")
    public String PageController(@PathVariable String page) {
        return page;
    }
}
