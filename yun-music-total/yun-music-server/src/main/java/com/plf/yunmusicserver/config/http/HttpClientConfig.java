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
    public RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory simpleClientHttpRequestFactory(){
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        clientHttpRequestFactory.setReadTimeout(readTimeout);
        return clientHttpRequestFactory;
    }

    @Bean("httpsRestTemplate")
    public RestTemplate httpsRestTemplate(HttpComponentsClientHttpRequestFactory factory) {
        try {
            RestTemplate restTemplate = new RestTemplate(factory);
            return restTemplate;
        } catch (Exception e) {
            throw new RuntimeException("Unable to initiate RestTemplate for access wechat-proxy.");
        }
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//        TrustStrategy acceptingTrustStrategy = (x509Certificates, authType) -> true;
//        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
//        LayeredConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
//
//        Registry<ConnectionSocketFactory> sfr = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", csf != null ? csf : SSLConnectionSocketFactory.getSocketFactory()).build();
//        PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(sfr);
////        pollingConnectionManager.setMaxTotal(maxTotal);
////        pollingConnectionManager.setDefaultMaxPerRoute(perRoute);
//
//        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).setConnectionManager(pollingConnectionManager).build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        //factory.setHttpClient(httpClient);
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(6000);
        factory.setConnectionRequestTimeout(60000);
        return factory;
    }

}