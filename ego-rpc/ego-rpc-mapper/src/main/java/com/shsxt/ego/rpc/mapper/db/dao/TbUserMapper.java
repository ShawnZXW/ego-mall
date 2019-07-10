package com.shsxt.ego.rpc.mapper.db.dao;

import com.shsxt.ego.rpc.pojo.TbUser;
import org.apache.ibatis.annotations.Param;

public interface TbUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);

    /**
     *  校验 user 对象唯一性
     *
     * @param param 校验参数
     * @param type 校验类型
     * @return
     */
    TbUser userCheck(@Param("param") String param, @Param("type") Integer type);
}