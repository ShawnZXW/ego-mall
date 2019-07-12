package com.shsxt.ego.rpc.query;

import java.io.Serializable;

/**
 * 商品内容查询
 *
 * @author Shawn
 * @date 2019-07-10 21:59
 */
public class ContentQuery extends BaseQuery implements Serializable {

    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
