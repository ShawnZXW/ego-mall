package com.shsxt.ego.rpc.mapper.db.dao;

import com.shsxt.ego.rpc.pojo.TbItemCatDesc;

public interface TbItemCatDescMapper {
    int deleteByPrimaryKey(Long itemId);

    int insert(TbItemCatDesc record);

    int insertSelective(TbItemCatDesc record);

    TbItemCatDesc selectByPrimaryKey(Long itemId);

    int updateByPrimaryKeySelective(TbItemCatDesc record);

    int updateByPrimaryKeyWithBLOBs(TbItemCatDesc record);

    int updateByPrimaryKey(TbItemCatDesc record);
}