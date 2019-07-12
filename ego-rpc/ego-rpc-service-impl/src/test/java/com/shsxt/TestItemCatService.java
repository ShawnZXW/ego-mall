package com.shsxt;


import com.shsxt.ego.common.model.BigPicture;
import com.shsxt.ego.rpc.pojo.TbContent;
import com.shsxt.ego.rpc.pojo.TbItemCat;
import com.shsxt.ego.rpc.service.IContentService;
import com.shsxt.ego.rpc.service.IItemCatService;
import com.shsxt.ego.rpc.service.IItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:spring/applicationContext-dao.xml",
        "classpath:spring/applicationContext-service.xml",
        "classpath:spring/applicationContext-tx.xml",
        "classpath:spring/applicationContext-redis.xml"} )

/**
 * 测试商品内容业务
 *
 * - 测试 redis 存储
 *
 */
public class TestItemCatService {

    @Resource
    private IContentService contentService;


    /**
     * -TODO
     * 这个测试该怎么写
     * -line 43
     */
    @Test
    public  void test01(){
        List<BigPicture> pictures = contentService.queryContentsByCategoryId(89L);

        pictures.forEach(i->{
            System.out.println(i);
        });
        System.out.println("已经打印完所有商品内容信息");
    }

}
