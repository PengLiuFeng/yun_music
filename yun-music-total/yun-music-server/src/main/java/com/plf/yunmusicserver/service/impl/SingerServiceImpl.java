package com.plf.yunmusicserver.service.impl;

import com.plf.yunmusicentity.commonhttp.ResponseResult;
import com.plf.yunmusicserver.dao.SingerMapper;
import com.plf.yunmusicserver.entity.Singer;
import com.plf.yunmusicserver.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class SingerServiceImpl implements SingerService {
    @Resource
    private SingerMapper singerMapper;

    @Override
    public ResponseResult<List<Singer>> querySingerList() {
        List<Singer> singerList = singerMapper.queryAllData();
        if (CollectionUtils.isEmpty(singerList)){
            log.debug("歌手信息查询为空");
            singerList = Collections.EMPTY_LIST;
        }

        return ResponseResult.success(singerList);
    }
}
