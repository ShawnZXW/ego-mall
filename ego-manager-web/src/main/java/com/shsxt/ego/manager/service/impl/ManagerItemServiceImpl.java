package com.shsxt.ego.manager.service.impl;

import com.shsxt.ego.common.model.EgoResult;
import com.shsxt.ego.common.model.PageResult;
import com.shsxt.ego.manager.service.IManagerItemService;
import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.pojo.TbItemCatDesc;
import com.shsxt.ego.rpc.query.ItemQuery;
import com.shsxt.ego.rpc.service.IItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class ManagerItemServiceImpl implements IManagerItemService {

    // 注入远程服务的代理对象
    @Resource
    private IItemService itemServiceProxy;


    /**
     * 商品分页展示
     *
     * @param itemQuery
     * @return
     */
    @Override
    public PageResult<TbItem> itemList(ItemQuery itemQuery) {
        return itemServiceProxy.queryItemsListByParams(itemQuery);
    }

    /**
     * 下架
     *
     * @param ids
     * @return
     */
    @Override
    public EgoResult reshelf(Long[] ids) {
        return itemServiceProxy.updateItemStatusBatch(ids, 1);
    }

    /**
     * 上架
     *
     * @param ids
     * @return
     */
    @Override
    public EgoResult instock(Long[] ids) {
        return itemServiceProxy.updateItemStatusBatch(ids, 2);
    }

    /**
     * 批量删除商品
     *
     * @param ids
     * @return
     */
    @Override
    public EgoResult deleteItemBatch(Long[] ids) {
        return itemServiceProxy.deleteItemBatch(ids);
    }

    /**
     * 保存商品
     *
     * @param item
     * @param itemCatDesc
     * @return
     */
    @Override
    public EgoResult saveItem(TbItem item, TbItemCatDesc itemCatDesc) {
        return null;
    }
}
