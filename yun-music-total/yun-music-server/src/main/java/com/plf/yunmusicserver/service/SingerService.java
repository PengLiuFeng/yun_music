package com.plf.yunmusicserver.service;

import com.plf.yunmusicentity.commonhttp.ResponseResult;
import com.plf.yunmusicserver.entity.Singer;

import java.util.List;

public interface SingerService {
    /**
     * 查询所有的歌手信息接口
     * @return
     */
    ResponseResult<List<Singer>> querySingerList();
}
