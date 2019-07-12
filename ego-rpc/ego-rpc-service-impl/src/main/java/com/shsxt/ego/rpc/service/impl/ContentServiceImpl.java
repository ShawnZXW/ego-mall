package com.shsxt.ego.rpc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.ego.common.model.BigPicture;
import com.shsxt.ego.common.model.EgoResult;
import com.shsxt.ego.common.model.PageResult;
import com.shsxt.ego.rpc.mapper.db.dao.TbContentMapper;
import com.shsxt.ego.rpc.pojo.TbContent;
import com.shsxt.ego.rpc.query.ContentQuery;
import com.shsxt.ego.rpc.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 门户内容业务实现类
 * - 使用 redis 来存储数据
 * - 由类目id查询数据库内容
 *
 * @author Shawn
 * @date 2019-07-10 18:34
 */
@Service
public class ContentServiceImpl implements IContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOperations;

    /**
     * 商品内容分页查询 & redis 技术
     *
     * @param contentQuery
     * @return
     */
    @Override
    public PageResult<TbContent> queryContentsByParams(ContentQuery contentQuery) {

        PageHelper.startPage(contentQuery.getPage(), contentQuery.getRows());
        // 这个key的索引有三个 cid、page、rows
        String key = "content::queryContentsByParams::cid::"
                + contentQuery.getCategoryId()
                + "::page::" + contentQuery.getPage()
                + "::rows::" + contentQuery.getRows();
        List<TbContent> itemList = null;
        if (redisTemplate.hasKey(key)) {
            // 查询 redis 的值
            itemList = (List<TbContent>) valueOperations.get(key);
        }else {// redis 没值，去数据库查
            itemList = contentMapper.queryContentsByParams(contentQuery);
            if (null != itemList && itemList.size() > 0) {
                // 将从数据库查询到值存到 redis 中
                valueOperations.set(key, itemList);
            }
        }

        PageInfo<TbContent> pageInfo = new PageInfo<>();

        PageResult<TbContent> pageResult = new PageResult<>();
        pageResult.setRows(pageInfo.getList());
        pageResult.setTotal(pageInfo.getTotal());
        return pageResult;
    }


    /**
     * 由 cid 查询内容，并使用 redis 作为缓存
     *
     * @param cid
     * @return
     */
    @Override
    public List<BigPicture> queryContentsByCategoryId(Long cid) {
        List<BigPicture> pictures = null;
        List<TbContent> contents = null;
        // 给 key 赋值,追加索引
        // content 表示表名，query... 表示方法名，cid表示索引
        String key = "content::queryContentsByCategoryId::cid::" + cid;

        // 对 redis 二级数据库进行判断
        if (redisTemplate.hasKey(key)) {// redis 中能查到值
            // 就从 redis 中取值
            contents = (List<TbContent>) valueOperations.get(key);
        } else {// 去数据库查找值
            // 执行数据库的查询方法
            contents = contentMapper.queryContentsByCategoryId(cid);
            // 对查询结果判断
            if (contents != null & contents.size() > 0) {// 数据库能查到
                // 将值添加到 redis 中
                valueOperations.set(key, contents);
            }
        }

        // 对查到的内容 contents 进行处理
        if (null != contents & contents.size() > 0) {

            pictures = new ArrayList<BigPicture>();
            // finalPicture 将不可修改
            List<BigPicture> finalPictures = pictures;
            // 将查询到的 contents 循环添加到容器中
            // jdk8 消费型接口
            contents.forEach(c -> {
                // 构建 BigPicture 的实例
                BigPicture bigPicture = new BigPicture();
                bigPicture.setAlt("ego商城");
                bigPicture.setHref(c.getUrl());
                bigPicture.setSrc(c.getPic());
                // 添加备用图片
                bigPicture.setSrcb(c.getPic2());
                // 添加 bigPicture 实例
                finalPictures.add(bigPicture);
            });
        }
        return pictures;
    }


    /**
     * 保存缓存内容
     * -清除 key 值以后，查询操作将会缓存穿透，
     * - 自动查询数据库，并添加到 redis
     *
     * @param content
     * @return
     */
    @Override
    public EgoResult save(TbContent content) {
        // 执行数据库插入方法
        contentMapper.insertSelective(content);

        // 清除单一的key
/*        String key = "content::queryContentsByCategoryId::cid::"+content.getCategoryId();
        redisTemplate.delete(key);
        */

        // 匹配指定的keys 达到缓存同步的效果
        // content::* 表示匹配获取 keys
        redisTemplate.delete(redisTemplate.keys("content::*"));
        return new EgoResult();
    }

    /**
     * 更新内容
     *
     * @param content
     * @return
     */
    @Override
    public EgoResult updateContent(TbContent content) {
        // 执行数据库更新方法
        contentMapper.updateByPrimaryKeySelective(content);
        // 缓存同步处理
        // 清除单个 key
//        String key = "content::queryContentByCategoryId::cid::"+content.getCategoryId();
//        redisTemplate.delete(key);

        // 匹配指定的 keys 缓存同步
        redisTemplate.delete(redisTemplate.keys("content::*"));
        return new EgoResult();
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Override
    public EgoResult delete(Long[] ids) {
        // 执行数据库批量删除方法
        contentMapper.deleteBatch(ids);
        // 模糊匹配广告内容 keys
        Set<String> keys = redisTemplate.keys("content::*");
        // 清除缓存，即redis 中数据的 keys
        redisTemplate.delete(keys);
        return new EgoResult();
    }


}
