package com.plf.yunmusicserver.contoller;

import com.plf.yunmusicentity.commonhttp.ResponseResult;
import com.plf.yunmusicserver.entity.User;
import com.plf.yunmusicserver.service.UserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping(value = "api/userLogin")
@Api(value = "用户登录，注册控制器", tags = "用于用户登录，注册接口控制")
@CrossOrigin(value = "http://localhost:8080", maxAge = 1800, allowedHeaders ="*")
public class UserLoginController {


    @Autowired
    private UserLoginService userLoginService;

    @ApiOperation(value = "【登录】用户登录接口")
    @GetMapping(value = "loginIn")
    public ResponseResult<User> userLoginIn(@RequestParam(value = "recode") String recode,
                                            @RequestParam(value = "password") String password) {
        return userLoginService.loginIn(recode, password);
    }

}
