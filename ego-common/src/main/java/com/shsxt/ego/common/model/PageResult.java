package com.shsxt.ego.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * 封装 datagrid 控件需要的数据模型
 *
 * @Author Shawn
 * @param <T>
 */
public class PageResult<T> implements Serializable {
    private Long total;
    private List<T> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
