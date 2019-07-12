package com.shsxt.ego.manager.service;

import com.shsxt.ego.common.model.EgoResult;
import com.shsxt.ego.common.model.PageResult;
import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.pojo.TbItemCatDesc;
import com.shsxt.ego.rpc.query.ItemQuery;

public interface IManagerItemService {
    /**
     * 商品信息的分页查询
     *
     * @param itemQuery
     * @return
     */
    PageResult<TbItem> itemList(ItemQuery itemQuery);

    /**
     * 商品信息的上架
     *
     * @param itemIds
     * @return
     */
    EgoResult reshelf(Long[] itemIds);

    /**
     * 商品信息的下架
     *
     * @param itemIds
     * @return
     */
    EgoResult instock(Long[] itemIds);


    /**
     * 批量删除商品
     *
     * @param ids
     * @return
     */
    EgoResult deleteItemBatch(Long[] ids);

    /**
     * 商品保存
     *
     * @param item
     * @param itemCatDesc
     * @return
     */
    EgoResult saveItem(TbItem item, TbItemCatDesc itemCatDesc);


}
