package com.shsxt.ego.manager.service;


import com.shsxt.ego.common.model.EgoResult;
import com.shsxt.ego.common.model.PageResult;
import com.shsxt.ego.rpc.pojo.TbContent;
import com.shsxt.ego.rpc.query.ContentQuery;

/**
 * 管理内容业务接口
 */
public interface IManagerContentService {

    PageResult<TbContent> queryContentsByParams(ContentQuery contentQuery);

    EgoResult delete(Long[] ids);

    EgoResult save(TbContent content);
}
