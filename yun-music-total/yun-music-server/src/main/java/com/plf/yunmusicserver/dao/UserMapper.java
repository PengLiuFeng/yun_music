package com.plf.yunmusicserver.dao;

import com.plf.yunmusicserver.entity.User;


public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 登录查询
     * @param user
     * @return
     */
    User selectByUserLoginIn(User user);
}