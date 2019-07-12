package com.shsxt.ego.rpc.mapper.db.dao;

import com.shsxt.ego.rpc.pojo.TbContent;
import com.shsxt.ego.rpc.query.ContentQuery;

import java.util.List;

public interface TbContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbContent record);

    int insertSelective(TbContent record);

    TbContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbContent record);

    int updateByPrimaryKeyWithBLOBs(TbContent record);


    int updateByPrimaryKey(TbContent record);

    /**
     * 通过 cid 查询内容
     *
     * @param cid
     * @return
     */
    List<TbContent> queryContentsByCategoryId(Long cid);


    /**
     * 分页查询内容
     *
     * @param contentQuery
     * @return
     */
    List<TbContent> queryContentsByParams(ContentQuery contentQuery);

    /**
     * 批量删除
     *
     * @param ids
     */
    int deleteBatch(Long[] ids);
}