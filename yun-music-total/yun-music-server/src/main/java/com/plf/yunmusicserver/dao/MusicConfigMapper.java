package com.plf.yunmusicserver.dao;

import com.plf.yunmusicserver.entity.MusicConfig;

public interface MusicConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(MusicConfig record);

    int insertSelective(MusicConfig record);

    MusicConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MusicConfig record);

    int updateByPrimaryKeyWithBLOBs(MusicConfig record);

    int updateByPrimaryKey(MusicConfig record);
}