package com.plf.yunmusicserver.contoller;

import com.plf.yunmusicentity.commonhttp.ResponseResult;
import com.plf.yunmusicserver.entity.Singer;
import com.plf.yunmusicserver.service.SingerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/singer")
@Api(value = "歌手控制器", tags = "歌手信息控制器")
//TODO 后去全部改成分页的
public class SingerController {
    @Autowired
    private SingerService singerService;

    @ApiOperation(value = "【服务】歌手", notes = "获取所有的歌手信息")
    @RequestMapping("singerListQuery")
    public ResponseResult<List<Singer>> singerListQuery(){
        return singerService.querySingerList();
    }
}
