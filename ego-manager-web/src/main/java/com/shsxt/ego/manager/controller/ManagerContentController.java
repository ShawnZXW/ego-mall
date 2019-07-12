package com.shsxt.ego.manager.controller;

import com.shsxt.ego.common.model.EgoResult;
import com.shsxt.ego.common.model.PageResult;
import com.shsxt.ego.manager.service.IManagerContentService;
import com.shsxt.ego.rpc.pojo.TbContent;
import com.shsxt.ego.rpc.query.ContentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 内容业务控制器
 *
 * @author Shawn
 * @date 2019-07-10 22:46
 */
@Controller
public class ManagerContentController {
    @Autowired
    private IManagerContentService contentService;

    /**
     * 内容分页查询
     *
     * @param contentQuery
     * @return
     */
    @RequestMapping("/content/query/list")
    @ResponseBody
    public PageResult<TbContent> queryContentsByParams(ContentQuery contentQuery) {
        return contentService.queryContentsByParams(contentQuery);
    }


    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("content/delete")
    @ResponseBody
    public EgoResult delete(Long[] ids) {
        return contentService.delete(ids);
    }


    /**
     * 保存
     *
     * @param content
     * @return
     */
    @RequestMapping("content/save")
    @ResponseBody
    public EgoResult save(TbContent content) {
        return contentService.save(content);
    }


}
