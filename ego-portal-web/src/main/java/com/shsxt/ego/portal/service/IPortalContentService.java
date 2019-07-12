package com.shsxt.ego.portal.service;

import com.shsxt.ego.common.model.BigPicture;

import java.util.List;

/**
 * 门户内容业务
 *
 * @author Shawn
 * @date 2019-07-10 18:24
 */
public interface IPortalContentService {

    /**
     * 由类目id查询门户内容
     *
     * @param cid
     * @return
     */
    List<BigPicture> queryContentsByCategoryId(Long cid);

}
