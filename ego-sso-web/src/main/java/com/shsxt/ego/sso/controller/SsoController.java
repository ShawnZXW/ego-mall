package com.shsxt.ego.sso.controller;

import com.shsxt.ego.common.model.EgoResult;
import com.shsxt.ego.rpc.pojo.TbUser;
import com.shsxt.ego.sso.service.ISsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * sso 控制器
 *
 * @author Shawn
 * @date 2019-07-08 11:19
 */
@Controller
public class SsoController {

    @Autowired
    private ISsoService ssoService;

    /**
     * 唯一性校验
     *
     * @param param
     * @param type
     * @return
     */
    @RequestMapping("user/check/{param}/{type}")
    @ResponseBody
    public EgoResult  userCheck(@PathVariable String param, @PathVariable Integer type){
        return ssoService.userCheck(param, type);
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @RequestMapping("user/register")
    @ResponseBody
    public EgoResult userRegister(TbUser user) {
        return ssoService.saveUser(user);
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("user/login")
    @ResponseBody
    public EgoResult loginCheck(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        return ssoService.loginCheck(username, password, request, response);
    }

    /**
     * 由 token 获取用户信息
     * @param token
     * @param callback
     * @return
     */
    @RequestMapping("user/token/{token}")
    @ResponseBody
    public MappingJacksonValue userInfo(@PathVariable String token, @RequestParam(required = false) String callback){

        EgoResult result = ssoService.userInfo(token);

        MappingJacksonValue value = new MappingJacksonValue(result);

        if (!StringUtils.isEmpty(callback)) {
            value.setJsonpFunction(callback);
        }
        return value;
    }


    /**
     * 登出
     *
     * @param token
     * @param callback
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("user/logout/{token}")
    public MappingJacksonValue userInfo(@PathVariable String token, @RequestParam(required = false) String  callback, HttpServletRequest request, HttpServletResponse response){
        EgoResult result=ssoService.userLogout(token,request,response);
        MappingJacksonValue value =new MappingJacksonValue(result);

        if(!StringUtils.isEmpty(callback)){
            value.setJsonpFunction(callback);
        }
        return value;


    }

}
