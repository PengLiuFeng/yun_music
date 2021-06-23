package com.plf.yunmusicserver.contoller;

import com.plf.yunmusicentity.commonhttp.ResponseResult;
import com.plf.yunmusicserver.annotation.LoginRequired;
import com.plf.yunmusicserver.entity.SongList;
import com.plf.yunmusicserver.service.SongListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/song")
@Api(value = "歌曲控制器", tags = "歌曲接口开放服务")
@LoginRequired
//TODO 后去全部改成分页的
public class SongController {


    @Autowired
    private SongListService songListService;

    @ApiOperation(value = "【服务】歌单接口查询", notes = "为首页提供歌单列表")
    @RequestMapping("songListQuery")
    public ResponseResult<List<SongList>> songListQuery(){
        return songListService.querySongList();
    }
}
