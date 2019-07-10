package com.shsxt.ego.sso.service.impl;

import com.shsxt.ego.commom.utils.CookieUtils;
import com.shsxt.ego.commom.utils.JsonUtils;
import com.shsxt.ego.common.model.EgoResult;
import com.shsxt.ego.rpc.pojo.TbUser;
import com.shsxt.ego.rpc.service.IUserService;
import com.shsxt.ego.sso.service.ISsoService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 * sso 单点登录业务实现类
 * - 校验唯一性的封装
 * - 注册功能
 * - 登录功能
 *
 * @author Shawn
 * @date 2019-07-08 11:22
 */
@Service
public class SsoServiceImpl implements ISsoService {

    // 注入远程服务代理
    @Resource
    private IUserService userServiceProxy;

    // 注入 redis 的远程代理服务
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 登录
     * - 添加创建时间以及更新时间
     * - 密码加密
     *
     * @param user
     * @return
     */
    @Override
    public EgoResult saveUser(TbUser user) {
        user.setCreated(new Date());
        user.setUpdated(new Date());
        // 设置密码，MD5 加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return userServiceProxy.saveUser(user);
    }

    /**
     * 登录验证以及实现用户信息保存
     * - 登录验证
     * -   验证非空
     * -   用户名存在性校验
     * -   密码一致性校验
     * - 用户信息保存
     * -  使用 redis 存储 key 值（采用方案 2 ）
     * -    方案 1、使用 userId ,
     * -        缺点：会暴露userId,不安全
     * -    方案 2、使用 uuid，token的令牌机制
     * -        优点：时空一致性
     * -    方案 2 的细节见方法中的注释
     *
     * @param username 用户名
     * @param password 用户密码
     * @param request  请求对象
     * @param response 响应对象
     * @return
     */
    @Override
    public EgoResult loginCheck(String username,
                                String password,
                                HttpServletRequest request,
                                HttpServletResponse response
    ) {
        EgoResult result = new EgoResult();
        // 非空校验
        if (username == null && password == null) {
            result.setStatus(500);
            result.setMsg("用户名或密码不存在");
            return result;
        }
        // 用户名存在性校验
        TbUser user = userServiceProxy.queryUserByName(username);
        if (user == null) {
            result.setStatus(500);
            result.setMsg("该用户不存在或已注销账号");
            return result;
        }
        // 密码一致性校验,记得md5加密后比较
        password = DigestUtils.md5DigestAsHex(password.getBytes());
//        user.getPassword() != password
        if (!password.equals(user.getPassword())) {
            result.setStatus(500);
            result.setMsg("输入的密码错误");
            return result;
        }


        // 将 uuid 作为 token 以 redis 存储，实现用户信息保存

        // 得到 String 类型的随机 uuid 作为 token
        String token = UUID.randomUUID().toString();
        // 将 token 以及 user 对象（String 格式）存储到 redis 中
        redisTemplate.opsForValue().set(token, JsonUtils.objectToJson(user));
        // 将 request、response和token 信息全部存在cookie中
        CookieUtils.setCookie(request, response, "sso_token", token);
        result.setMsg("用户登录成功");
        return result;
    }


    /**
     * 用户唯一性检验
     *
     * @param param 校验参数
     * @param type  校验参数类型
     * @return
     */
    @Override
    public EgoResult userCheck(String param, Integer type) {
        return userServiceProxy.userCheck(param, type);
    }

    /**
     * 由 token 来获取用户信息
     * - 加上跨域 jsonp 实现单点登录
     *
     * @param token
     * @return
     */
    @Override
    public EgoResult userInfo(String token) {
        TbUser user = JsonUtils.jsonToPojo((String) redisTemplate.opsForValue().get(token), TbUser.class);
        EgoResult result = new EgoResult();
        result.setData(user);
        return result;
    }

    /**
     * 登出
     * - 清除 token
     * - 清除 cookie
     *
     * @param token
     * @param request
     * @param response
     * @return
     */
    @Override
    public EgoResult userLogout(String token, HttpServletRequest request, HttpServletResponse response) {
        // 清除 token
        redisTemplate.delete(token);
        // 清除cookie
        CookieUtils.deleteCookie(request, response, token);
        return new EgoResult();
    }


}
