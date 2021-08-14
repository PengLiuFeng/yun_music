package com.plf.yunmusicserver.service.impl;

import com.google.common.base.Strings;
import com.plf.yunmusiccommon.util.JacksonUtils;
import com.plf.yunmusiccommon.util.JavaObjectExchangeUtils;
import com.plf.yunmusicentity.commonhttp.ResponseResult;
import com.plf.yunmusicentity.dto.user.UserDTO;
import com.plf.yunmusicentity.enums.ResponseStatusEnum;
import com.plf.yunmusicserver.common.mq.send.SendUserToken;
import com.plf.yunmusicserver.dao.UserMapper;
import com.plf.yunmusicserver.entity.User;
import com.plf.yunmusicserver.exception.TokenException;
import com.plf.yunmusicserver.service.TokenService;
import com.plf.yunmusicserver.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {


    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SendUserToken sendUserToken;


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
                redisTemplate.opsForValue().set(userDTO.getToken(),userDTO,1000, TimeUnit.SECONDS);
                sendUserToken.doSend(JacksonUtils.javaBeanToString(userDTO));
                return ResponseResult.success(userDTO);
            }catch (TokenException e){
                log.error("用户{}获取token报错：{}",user.getUserNickName() , JacksonUtils.javaBeanToString(e));
                return ResponseResult.error(ResponseStatusEnum.ERROR002.getCode(),ResponseStatusEnum.ERROR002.getMessage());
            }
        }
        return ResponseResult.error(ResponseStatusEnum.ERROR001.getCode(),ResponseStatusEnum.ERROR001.getMessage());
    }
}
