package com.shsxt.ego.portal.service.impl;

import com.shsxt.ego.common.model.CatNode;
import com.shsxt.ego.portal.service.IPortalItemCatService;
import com.shsxt.ego.rpc.pojo.TbItemCat;
import com.shsxt.ego.rpc.service.IItemCatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台门户商品类目业务实现类
 *
 * @author Shawn
 * @date 2019-07-05 19:06
 */
@Service
public class PortalItemCatServiceImpl implements IPortalItemCatService {

    //注入远程代理对象
    @Resource
    private IItemCatService itemCatServiceProxy;

    /**
     * 查询所有商品分类
     *
     * @return
     */
    @Override
    public Map<String, Object> getAllItemCats() {
        // 执行数据库查询操作
        List<TbItemCat> list = itemCatServiceProxy.queryAllItemCats();
        // 分类数据处理
        List result = getChildren(0L, list);
        // 将数据添加到 map 容器
        Map<String, Object> map = new HashMap<>();
        map.put("data", result);
        return map;
    }

    /**
     * 获取子节点
     *
     * @param parentId
     * @param list
     * @return
     */
    private List getChildren(long parentId, List<TbItemCat> list) {
        // 盛放指定分类下的所有子分类信息的list集合result
        List<Object> result = new ArrayList<>();
        // 遍历 result 集合
        for (TbItemCat itemCat : list) {
            // 根据父节点判断节点类型
            if (itemCat.getParentId().equals(parentId)) {
                if (itemCat.getIsParent()) {
                    // 构建CatNode对象
                    CatNode catNode = new CatNode();
                    if (itemCat.getParentId().equals(0L)) {
                        // 如果是一级分类  "<a href='/products/1.html'>图书、音像、电子书刊</a>"
                        catNode.setName("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
                    } else {
                        // // 如果是二级分类  "电子书刊"
                        catNode.setName(itemCat.getName());
                    }
                    //  "/products/2.html"
                    catNode.setUrl("/products/" + itemCat.getId() + "html");
                    // 递归遍历 list
                    catNode.setList(getChildren(itemCat.getId(), list));
                    // 将节点添加到 list 集合中
                    result.add(catNode);
                } else {
                    // 如果 itemCat 表示三级分类  "/products/3.html|电子书"
                    result.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
                }
            }
        }
        return result;
    }
}
