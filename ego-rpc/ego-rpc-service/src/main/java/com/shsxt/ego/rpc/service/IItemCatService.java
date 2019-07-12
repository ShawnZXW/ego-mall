package com.shsxt.ego.rpc.service;

import com.shsxt.ego.rpc.pojo.TbItemCat;

import java.util.List;

/**
 * 商品类目业务接口
 */
public interface IItemCatService {

    /**
     * 查询门户首页的商品类目
     *
     * @return
     */
    public List<TbItemCat> queryAllItemCats();
}
