package com.shsxt.ego.rpc.service;

import com.shsxt.ego.common.model.EgoResult;
import com.shsxt.ego.rpc.pojo.TbItemCatDesc;
import com.shsxt.ego.rpc.pojo.TbItemParam;
import com.shsxt.ego.rpc.query.ItemQuery;
import com.shsxt.ego.common.model.PageResult;
import com.shsxt.ego.rpc.pojo.TbItem;

import java.util.List;

/**
 * 商品业务接口
 */
public interface IItemService {

    /**
     * 实现商品信息的分页查询展示
     *
     * @param itemQuery
     * @return
     */
    PageResult<TbItem> queryItemsListByParams(ItemQuery itemQuery);

    // 简单的状态更新方法，不使用这种
    EgoResult updateItemStatus(TbItem item);

    /**
     * 完成商品上下架状态的修改
     * - 商品状态，1-正常，2-下架，3-删除
     *
     * @param itmeIds
     * @param type
     * @return
     */
    EgoResult updateItemStatusBatch(Long[] itmeIds, int type);

    /**
     * 商品批量删除
     *
     * @param ids
     * @return
     */
    EgoResult deleteItemBatch(Long[] ids);

    /**
     * 新增商品
     *
     * @param item
     * @param itemCatDesc
     * @return
     */
    EgoResult saveItem(TbItem item, TbItemCatDesc itemCatDesc);


}
