package com.plf.yunmusicserver.service.impl;

import com.plf.yunmusicentity.commonhttp.ResponseResult;
import com.plf.yunmusicserver.dao.SongListMapper;
import com.plf.yunmusicserver.entity.SongList;
import com.plf.yunmusicserver.service.SongListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class SongListServiceImpl implements SongListService {

    @Resource
    private SongListMapper songListMapper;

    @Override
    public ResponseResult<List<SongList>> querySongList() {
        List<SongList> result =  songListMapper.allDataQuery();
        if (CollectionUtils.isEmpty(result)){
            log.debug("当前歌单数据查询数据为空");
            result = Collections.EMPTY_LIST;
        }
        return ResponseResult.success(result);
    }
}
