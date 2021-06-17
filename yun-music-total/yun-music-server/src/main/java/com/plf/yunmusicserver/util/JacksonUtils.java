package com.plf.yunmusicserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author pengliufeng
 * @since 2021/04/09
 * <p>
 * jackson转换工具
 * </p>
 */
@Slf4j
public class JacksonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String javaBeanToString(Object object) {
        String result = "";
        try {
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("【json转换】json转换失败:{}", e.getMessage());
        }
        return result;
    }

    public static <T> T javaStringToBean(String json, Class<T> classType) {
        T result = null;
        try {
            result = objectMapper.readValue(json, classType);
        } catch (JsonProcessingException e) {
            log.error("【json转换】json转换失败:{}", javaBeanToString(e));
        }
        return result;
    }
}
