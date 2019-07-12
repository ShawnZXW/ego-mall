package com.shsxt.ego.manager.service.impl;

import com.shsxt.ego.common.model.EgoResult;
import com.shsxt.ego.common.model.PageResult;
import com.shsxt.ego.manager.service.IManagerContentService;
import com.shsxt.ego.rpc.pojo.TbContent;
import com.shsxt.ego.rpc.query.ContentQuery;
import com.shsxt.ego.rpc.service.IContentService;
import org.springframework.stereotype.Service;

/**
 * @author Shawn
 * @date 2019-07-10 22:30
 */
@Service
public class ManagerContentServiceImpl implements IManagerContentService {

    // 调用远程代理
    private IContentService contentServiceProxy;

    @Override
    public PageResult<TbContent> queryContentsByParams(ContentQuery contentQuery) {
        return contentServiceProxy.queryContentsByParams(contentQuery);
    }

    @Override
    public EgoResult delete(Long[] ids) {
        return contentServiceProxy.delete(ids);
    }

    @Override
    public EgoResult save(TbContent content) {
        return contentServiceProxy.save(content);
    }
}
