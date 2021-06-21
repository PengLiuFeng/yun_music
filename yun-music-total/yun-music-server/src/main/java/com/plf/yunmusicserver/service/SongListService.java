package com.plf.yunmusicserver.service;

import com.plf.yunmusicentity.commonhttp.ResponseResult;
import com.plf.yunmusicserver.entity.SongList;

import java.util.List;

public interface SongListService {

    /**
     * 获取当前的songList歌单
     * @return
     */
    ResponseResult<List<SongList>> querySongList();
}
