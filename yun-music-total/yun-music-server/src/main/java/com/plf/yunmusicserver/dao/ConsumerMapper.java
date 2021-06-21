package com.plf.yunmusicserver.dao;

import com.plf.yunmusicserver.entity.Consumer;

public interface ConsumerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Consumer record);

    int insertSelective(Consumer record);

    Consumer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Consumer record);

    int updateByPrimaryKey(Consumer record);
}