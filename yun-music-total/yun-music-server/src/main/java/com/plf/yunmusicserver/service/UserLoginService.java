package com.plf.yunmusicserver.service;

import com.plf.yunmusicentity.commonhttp.ResponseResult;
import com.plf.yunmusicserver.entity.User;

/**
 * @author pengliufeng
 * @since 2021/04/09
 * <p>
 *     用户登录接口
 * </p>
 */
public interface UserLoginService {

    /**
     * 登录接口
     */
    ResponseResult<User> loginIn(String recode , String password);
}
