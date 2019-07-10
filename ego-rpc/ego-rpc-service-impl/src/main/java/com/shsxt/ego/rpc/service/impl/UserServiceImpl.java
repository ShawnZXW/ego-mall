package com.shsxt.ego.rpc.service.impl;

import com.shsxt.ego.common.model.EgoResult;
import com.shsxt.ego.rpc.mapper.db.dao.TbUserMapper;
import com.shsxt.ego.rpc.pojo.TbUser;
import com.shsxt.ego.rpc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private TbUserMapper userMapper;

    @Override
    public TbUser queryUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public EgoResult saveUser(TbUser user) {
        // 将注册信息提交到数据库
        userMapper.insertSelective(user);
        return new EgoResult();
    }

    @Override
    public TbUser queryUserByName(String userName) {
        // 调用校验方法 userCheck,传入参数 1，表示
        return userMapper.userCheck(userName, 1);
    }

    @Override
    public EgoResult userCheck(String param, Integer type) {
        EgoResult result = new EgoResult();
        TbUser user = userMapper.userCheck(param,type);
        if (null != user) {
            // 用户存在，表示已注册
            result.setData(false);
        }else {
            // 用户不存在，表示未注册
            result.setData(true);
        }
        return result;
    }


}
