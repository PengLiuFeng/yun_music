package com.plf.yunmusicserver.service;

import com.plf.yunmusicserver.entity.User;
import com.plf.yunmusicserver.exception.TokenException;

public interface TokenService {

    /**
     * <p>
     *     获取用户登录token
     * </p>
     */
    String getToken(User user) throws TokenException;
}
