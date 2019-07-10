package com.shsxt.ego.manager.controller;

import com.shsxt.ego.rpc.pojo.TbUser;
import com.shsxt.ego.rpc.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private IUserService userServiceProxy;

    /**
     * 加载用户业务
     * @param userId
     * @return
     */
    @RequestMapping("user/queryUserByUserId/{userId}")
    @ResponseBody
    public TbUser queryUserByUserId(@PathVariable Long userId){
        return userServiceProxy.queryUserByUserId(userId);
    }

}
