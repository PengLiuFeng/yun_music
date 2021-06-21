package com.plf.yunmusicserver.dao;

import com.plf.yunmusicserver.entity.SongList;

import java.util.List;

public interface SongListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SongList record);

    int insertSelective(SongList record);

    SongList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SongList record);

    int updateByPrimaryKeyWithBLOBs(SongList record);

    int updateByPrimaryKey(SongList record);

    /**
     * 查询所有的值
     * @return
     */
    List<SongList> allDataQuery();
}