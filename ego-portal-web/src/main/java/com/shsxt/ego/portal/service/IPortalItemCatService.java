package com.shsxt.ego.portal.service;

import java.util.Map;

/**
 * 前台门户商品类目业务接口
 *
 * @author Shawn
 * @date 2019-07-05 19:04
 */
public interface IPortalItemCatService {


    /**
     * 获取前台首页的商品类目
     *
     * @return
     */
    public Map<String, Object> getAllItemCats();
}
