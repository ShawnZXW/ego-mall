package com.shsxt.ego.rpc.service;

import com.shsxt.ego.common.model.BigPicture;
import com.shsxt.ego.common.model.EgoResult;
import com.shsxt.ego.common.model.PageResult;
import com.shsxt.ego.rpc.pojo.TbContent;
import com.shsxt.ego.rpc.query.ContentQuery;

import java.util.List;

/**
 * 门户内容业务接口
 *
 * @author Shawn
 * @date 2019-07-10 18:31
 */
public interface IContentService {

    PageResult<TbContent> queryContentsByParams(ContentQuery contentQuery);

    List<BigPicture> queryContentsByCategoryId(Long cid);

    EgoResult save(TbContent content);

    EgoResult updateContent(TbContent content);

    EgoResult delete(Long[] ids);
}
