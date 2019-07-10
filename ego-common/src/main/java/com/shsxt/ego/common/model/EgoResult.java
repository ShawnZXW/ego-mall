package com.shsxt.ego.common.model;


import java.io.Serializable;

/**
 * 封装客户端发送上架，下架，
 * 删除商品请求后，
 * 需要响应的数据模型
 *
 * @author Shawn
 * @date 2019-07-04 20:26
 */

public class EgoResult implements Serializable {


    private Integer status = 200;   //响应状态

    private Object data;    //响应数据

    private String msg = "操作成功";    //响应消息

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public EgoResult(Integer status, Object data, String msg) {
        super();
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public EgoResult(Integer status, String msg) {
        super();
        this.status = status;
        this.msg = msg;
    }

    public EgoResult(Object data) {
        super();
        this.data = data;
        this.status = 200;
        this.msg = "ok";

    }

    public EgoResult() {
        super();
    }

    /***
     * 静态方法，返回 egoResult 对象
     * */
    public static EgoResult ok() {
        return new EgoResult(null);
    }
}
