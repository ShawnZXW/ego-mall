package com.shsxt.ego.rpc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.ego.common.model.EgoResult;
import com.shsxt.ego.common.model.PageResult;
import com.shsxt.ego.rpc.mapper.db.dao.TbItemDescMapper;
import com.shsxt.ego.rpc.mapper.db.dao.TbItemMapper;
import com.shsxt.ego.rpc.mapper.db.dao.TbItemParamItemMapper;
import com.shsxt.ego.rpc.pojo.TbItem;
import com.shsxt.ego.rpc.pojo.TbItemCatDesc;
import com.shsxt.ego.rpc.pojo.TbItemParam;
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

    @Autowired
    private TbItemDescMapper itemDescMapper;


    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    /**
     * 商品信息分页展示
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

    /**
     * 更新商品状态
     * -上架
     * -下架
     *
     * @param item
     * @return
     */
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

    /**
     * 批量删除商品
     * 思路：要删除三张表的数据，
     * - tb_item     商品表
     * -    修改商品状态为 3
     * - tb_item_desc    商品描述表
     * -    删除商品描述记录
     * - tb_item_param_item     商品规格参数模板表
     * -    删除商品规格记录
     *
     * @param ids 删除商品的 ids
     * @return
     */
    @Override
    public EgoResult deleteItemBatch(Long[] ids) {

        EgoResult result = new EgoResult();
        if (ids != null && ids.length > 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("ids", ids);
            // 执行数据库删除方法
            // 将商品的状态改为 3
            itemMapper.deleteItemBatch(param);
            // 删除商品描述记录
            itemDescMapper.deleteItemDescBatch(param);
            // 删除商品规格记录
            itemParamItemMapper.deleteParamItemBatch(param);
            return result;
        }
        // 传入的 ids 有问题
        result.setStatus(400);
        result.setMsg("系统正在繁忙，请稍后操作");
        return result;
    }

    /**
     * 新增商品类目
     *
     * @param item 商品
     * @param itemCatDesc 商品描述
     * @return
     */
    @Override
    public EgoResult saveItem(TbItem item, TbItemCatDesc itemCatDesc) {
        return null;
    }
}
