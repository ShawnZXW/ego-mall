package com.shsxt.ego.rpc.service.impl;

import com.shsxt.ego.rpc.mapper.db.dao.TbItemCatMapper;
import com.shsxt.ego.rpc.pojo.TbItemCat;
import com.shsxt.ego.rpc.service.IItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品类目业务实现类
 *
 * @author Shawn
 * @date 2019-07-05 18:38
 */
@Service
public class ItemCatServiceImpl implements IItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 查询所有商品类目
     * -
     * - 配置 redis 二级缓存
     * -方案：（配置缓存用 springData 的知识）
     * -  1、从 redis 缓存中获取缓存数据
     * -  若缓存存在，则获取缓存，返回
     * -  2、反之，缓存不存在（缓存穿透）
     * -   从数据库查询数据
     * -   数据库存在数据，获取数据，并将查询的数据放到redis缓存中
     *
     * @return
     */
    @Override
    public List<TbItemCat> queryAllItemCats() {
//        查询所有商品类目
        // 指定 key 值
        String key = "itemCat :: allItemCats";

        List<TbItemCat> itemCats = null;

        if (redisTemplate.hasKey(key)) {// 判断 key 是否存在，真 则返回 true
            // 打印信息
            System.out.println("从缓存中获取 list 集合");

            // 获取一条数据
            itemCats = (List<TbItemCat>) valueOperations.get(key);
        } else {
            itemCats = itemCatMapper.queryAllItemCats();

            if (null != itemCats && itemCats.size() > 0) {// 数组存在
                // 添加数据
                valueOperations.set(key, itemCats);
            }
        }
        return itemCats;
    }
}
