package com.shsxt.ego.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品详情控制器
 *
 * @author Shawn
 * @date 2019-07-12 09:31
 */
@Controller
public class ItemController {

    @RequestMapping("item/{itemId}")
    public String show(@PathVariable Long itemId) {
        System.out.println("商品id" + itemId);
        return "item";
    }


}
