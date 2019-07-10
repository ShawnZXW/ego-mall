package com.shsxt.ego.rpc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.ego.common.model.EgoResult;
import com.shsxt.ego.common.model.PageResult;
import com.shsxt.ego.rpc.mapper.db.dao.TbItemMapper;
import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.query.ItemQuery;
import com.shsxt.ego.rpc.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements IItemService {
    // 注入 mapper 接口代理对象
    @Autowired
    private TbItemMapper itemMapper;

    /**
     * 商品信息分页查询
     *
     * @param itemQuery
     * @return
     */
    @Override
    public PageResult<TbItem> queryItemsListByParams(ItemQuery itemQuery) {
        // 启动分页
        PageHelper.startPage(itemQuery.getPage(), itemQuery.getRows());
        // 执行数据库查询操作
        List<TbItem> itemList = itemMapper.queryItemsByParams(itemQuery);
        PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);
        PageResult<TbItem> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setRows(pageInfo.getList());
        return pageResult;
    }

    @Override
    public EgoResult updateItemStatus(TbItem item) {
        itemMapper.updateByPrimaryKeySelective(item);
        return new EgoResult();
    }

    /**
     * 商品的批量上架和下架
     *
     * @param ids
     * @param type
     * @return
     */
    @Override
    public EgoResult updateItemStatusBatch(Long[] ids, int type) {
        // 构建HashMap集合，并将参数放到集合中
        Map<String, Object> param = new HashMap<>();
        param.put("ids", ids);
        param.put("type", type);
        // 执行数据库更新操作
        itemMapper.updateItemStatusBatch(param);
        return new EgoResult();
    }
}
