package com.shsxt.ego.sso.service;

import com.shsxt.ego.common.model.EgoResult;
import com.shsxt.ego.rpc.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 单点登录业务接口
 * - 注册
 * - 登录
 * - 单点登录项目仅仅实现了注册和登录的简单功能
 * @author Shawn
 * @date 2019-07-08 11:20
 */
public interface ISsoService {

    /**
     * 实现用户注册
     * - 添加用户信息
     *
     * @param user
     * @return
     */
    EgoResult saveUser(TbUser user);

    /**
     * 进行用户账户的登录验证
     * -前台传来的用户名、密码
     * -request对象和response对象
     *
     * @param userName
     * @param request
     * @param response
     * @return
     */
    EgoResult loginCheck(String userName,String password, HttpServletRequest request, HttpServletResponse response);


    /**
     * 用户唯一性信息校验
     *
     * @param param
     * @param type
     * @return
     */
    EgoResult userCheck(String param, Integer type);

    /**
     * 根据令牌 token 获取用户信息
     *
     *
     * @param token
     * @return
     */
    EgoResult userInfo(String token);

    /**
     * 用户登出
     *
     * @param token
     * @param request
     * @param response
     * @return
     */
    EgoResult userLogout(String token, HttpServletRequest request, HttpServletResponse response);

}
