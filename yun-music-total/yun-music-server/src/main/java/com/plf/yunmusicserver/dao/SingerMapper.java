package com.plf.yunmusicserver.dao;

import com.plf.yunmusicserver.entity.Singer;

import java.util.List;

public interface SingerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Singer record);

    int insertSelective(Singer record);

    Singer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Singer record);

    int updateByPrimaryKey(Singer record);

    /**
     * 查询所有的歌手信息接口
     * @return
     */
    List<Singer> queryAllData();
}