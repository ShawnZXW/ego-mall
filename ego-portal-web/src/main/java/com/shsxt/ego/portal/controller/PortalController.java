package com.shsxt.ego.portal.controller;

import com.shsxt.ego.commom.utils.JsonUtils;
import com.shsxt.ego.common.model.BigPicture;
import com.shsxt.ego.portal.service.IPortalItemCatService;
import com.shsxt.ego.rpc.service.IContentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 门户商品类目控制器
 *
 * @author Shawn
 * @date 2019-07-05 20:07
 */
@Controller
public class PortalController {

    // 注入远程代理对象
    @Resource
    private IPortalItemCatService portalItemCatService;

    @Resource
    private IContentService contentServiceProxy;

    @RequestMapping(value = "/item/cat", produces = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")
    @ResponseBody
    public String getAllItemCats() {

        // 用 map 来接收查询结果
        Map<String, Object> map = portalItemCatService.getAllItemCats();
        // 将 map 对象序列化为 json 字符串
        return JsonUtils.objectToJson(map);
    }


    @RequestMapping(value = "/content/index/list", produces = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")
    @ResponseBody
    public String queryContentsByCategoryId(Long categoryId) {
        // 用list集合接收查询结果
        List<BigPicture> list = contentServiceProxy.queryContentsByCategoryId(categoryId);
        // 将 list 对象序列化为 json 字符串
        return JsonUtils.objectToJson(list);
    }



}
