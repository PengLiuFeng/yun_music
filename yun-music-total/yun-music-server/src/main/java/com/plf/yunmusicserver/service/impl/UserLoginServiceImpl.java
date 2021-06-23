package com.plf.yunmusicserver.service.impl;

import com.google.common.base.Strings;
import com.plf.yunmusiccommon.util.JacksonUtils;
import com.plf.yunmusiccommon.util.JavaObjectExchangeUtils;
import com.plf.yunmusicentity.commonhttp.ResponseResult;
import com.plf.yunmusicentity.dto.user.UserDTO;
import com.plf.yunmusicentity.enums.ResponseStatusEnum;
import com.plf.yunmusicserver.dao.UserMapper;
import com.plf.yunmusicserver.entity.User;
import com.plf.yunmusicserver.exception.TokenException;
import com.plf.yunmusicserver.service.TokenService;
import com.plf.yunmusicserver.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TokenService tokenService;


    @Override
    public ResponseResult<UserDTO> loginIn(String recode, String password) {
        if (Strings.isNullOrEmpty(recode) || Strings.isNullOrEmpty(password)) {
            log.error("【登录】当前用户的登录号和密码均为空！recode = {} ; password = {}", recode, password);
            return ResponseResult.error(ResponseStatusEnum.LOGIN_ERROR.getCode(), ResponseStatusEnum.LOGIN_ERROR.getMessage());
        }

        User user = new User();
        user.setUserCode(recode);
        user.setUserIphoneNumber(recode);
        user.setUserEmail(recode);
        user.setUserPassword(password);
        user = userMapper.selectByUserLoginIn(user);
        log.debug("【登录】当前登录用户{}", JacksonUtils.javaBeanToString(user));
        if (Objects.nonNull(user)) {
            UserDTO userDTO = JavaObjectExchangeUtils.objectExchange(user,UserDTO.class);
            try{
                userDTO.setToken(tokenService.getToken(user));
                return ResponseResult.success(userDTO);
            }catch (TokenException e){
                log.error("用户{}获取token报错：{}",user.getUserNickName() , JacksonUtils.javaBeanToString(e));
                return ResponseResult.error("-5","当前无法登录，请稍后再试！");
            }
        }
        return ResponseResult.error("-1001", "账号或者密码错误，请确认后重试");
    }
}
