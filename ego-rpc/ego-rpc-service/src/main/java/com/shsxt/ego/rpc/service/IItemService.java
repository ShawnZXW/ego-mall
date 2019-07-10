package com.shsxt.ego.rpc.service;

import com.shsxt.ego.common.model.EgoResult;
import com.shsxt.ego.rpc.query.ItemQuery;
import com.shsxt.ego.common.model.PageResult;
import com.shsxt.ego.rpc.pojo.TbItem;

import java.util.List;

/**
 * 商品服务接口
 */
public interface IItemService {

    /**
     * 实现商品信息的分页查询
     *
     * @param itemQuery
     * @return
     */
    public PageResult<TbItem> queryItemsListByParams(ItemQuery itemQuery);

    // 简单的状态更新方法，不使用这种
    public EgoResult updateItemStatus(TbItem item);

    /**
     * 完成商品上下架状态的修改
     * @param itmeIds
     * @param type
     * @return
     */
    public EgoResult updateItemStatusBatch(Long[] itmeIds,int type);


}
