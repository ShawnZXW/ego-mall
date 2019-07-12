package com.shsxt;


import com.shsxt.ego.rpc.service.IItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/applicationContext-dao.xml",
        "classpath:spring/applicationContext-service.xml",
        "classpath:spring/applicationContext-redis.xml",
        "classpath:spring/applicationContext-tx.xml"})
/**
 * 测试商品业务
 */
public class TestItemService {

    @Resource
    private IItemService itemService;

    /**
     * 测试商品批量删除功能
     */
    @Test
    public void test01() {
        Long[] ids = {679532L, 635906L};
        itemService.deleteItemBatch(ids);
        // 控制台能打印地址，说明值有效
        System.out.println("单元测试成功标志：" + itemService + "------------");
    }

}
