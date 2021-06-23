package com.plf.yunmusicserver.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.base.Strings;
import com.plf.yunmusicserver.entity.User;
import com.plf.yunmusicserver.exception.TokenException;
import com.plf.yunmusicserver.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(User user) throws TokenException{
        if (Objects.isNull(user) || Strings.isNullOrEmpty(user.getUserId())){
            throw new TokenException("用户信息不正确","-4");
        }
        Date start = new Date();
        Date end = new Date(start.getTime()+60*60*1000);
        return JWT.create().withAudience(user.getUserId()).withIssuedAt(start).withExpiresAt(end).sign(Algorithm.HMAC256(user.getUserPassword()));
    }
}
