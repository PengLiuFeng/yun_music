package com.plf.yunmusicserver.contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plf.yunmusicentity.commonhttp.ResponseResult;
import com.plf.yunmusicserver.entity.User;
import com.plf.yunmusicserver.util.JacksonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "api/userLogin")
@Api(value = "用户登录，注册控制器",tags = "用于用户登录，注册接口控制")
public class UserLoginController {

    @ApiOperation(value = "【登录】用户登录接口")
    @GetMapping(value = "loginIn")
    public String userLoginIn(@RequestParam(value = "recode") String recode ,
                                     @RequestParam(value = "password") String password) throws JsonProcessingException {
        return JacksonUtils.javaBeanToString(ResponseResult.success(new User()));
    }
}
