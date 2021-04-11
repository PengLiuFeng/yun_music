package com.plf.yunmusicserver.dao;

import com.plf.yunmusicserver.entity.Power;


public interface PowerMapper {
    int deleteByPrimaryKey(String powerId);

    int insert(Power record);

    int insertSelective(Power record);

    Power selectByPrimaryKey(String powerId);

    int updateByPrimaryKeySelective(Power record);

    int updateByPrimaryKeyWithBLOBs(Power record);

    int updateByPrimaryKey(Power record);
}