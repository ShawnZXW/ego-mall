package com.shsxt.ego.portal.service.impl;

import com.shsxt.ego.common.model.BigPicture;
import com.shsxt.ego.portal.service.IPortalContentService;
import com.shsxt.ego.rpc.service.IContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 门户内容业务实现类
 *
 * @author Shawn
 * @date 2019-07-10 18:29
 */
@Service
public class PortalContentServiceImpl implements IPortalContentService {


    // 注入远程代理对象
    @Resource
    private IContentService contentServiceProxy;

    @Override
    public List<BigPicture> queryContentsByCategoryId(Long cid) {
        return contentServiceProxy.queryContentsByCategoryId(cid);
    }
}
