/**
 * OPay Inc.
 * Copyright (c) 2016-2022 All Rights Reserved.
 */
package com.plf.yunmusicserver.config.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;


/**
 * @author liufeng.peng
 * @version $Id: HttpUtils.java, v 0.1 2022-06-02 16:26 liufeng.peng Exp $$
 */
@Slf4j
@Configuration
public class HttpClientConfig {

    @Value("${http.read.timeout:50000}")
    private int readTimeout;

    @Value("${http.connection.timeout:50000}")
    private int connectTimeout;

    @Bean("restTemplate")
    public RestTemplate restTemplate(SimpleClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }

    @Bean
    public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        clientHttpRequestFactory.setReadTimeout(readTimeout);
        return clientHttpRequestFactory;
    }

}