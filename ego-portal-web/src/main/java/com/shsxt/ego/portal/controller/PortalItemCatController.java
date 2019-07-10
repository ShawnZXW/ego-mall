package com.shsxt.ego.portal.controller;

import com.shsxt.ego.commom.utils.JsonUtils;
import com.shsxt.ego.portal.service.IPortalItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 门户商品类目控制器
 *
 * @author Shawn
 * @date 2019-07-05 20:07
 */
@Controller
public class PortalItemCatController {

    // 注入service对象
    @Resource
    private IPortalItemCatService portalItemCatService;

    @RequestMapping(value = "/item/cat",produces= MediaType.TEXT_HTML_VALUE+";charset=UTF-8")
    @ResponseBody
    public String getAllItemCats(){

        // 执行数据库查询方法
        Map<String,Object> map = portalItemCatService.getAllItemCats();
        // 将 map 对象序列化为 json 字符串
        return JsonUtils.objectToJson(map);
    }
}
