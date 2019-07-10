package com.shsxt.ego.rpc.service;

import com.shsxt.ego.common.model.EgoResult;
import com.shsxt.ego.rpc.pojo.TbUser;

public interface IUserService {

    /**
     * 通过 userId 查询 user 对象
     * @param userId
     * @return
     */
    TbUser queryUserByUserId(Long userId);

    /**
     * 用户唯一性信息校验
     *
     * @param param 校验参数
     * @param type 校验类型
     * @return
     */
    EgoResult userCheck(String param, Integer type);

    /**
     * 添加 user 对象
     *
     * @param tbUser
     * @return
     */
    EgoResult saveUser(TbUser tbUser);


    /**
     * 通过姓名查询 user 用户
     *
     * @param userName
     * @return
     */
    TbUser queryUserByName(String userName);
}
