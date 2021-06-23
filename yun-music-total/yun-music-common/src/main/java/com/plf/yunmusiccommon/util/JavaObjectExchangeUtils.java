package com.plf.yunmusiccommon.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public class JavaObjectExchangeUtils {
    private static final Logger logger = LoggerFactory.getLogger(JavaObjectExchangeUtils.class);

    private JavaObjectExchangeUtils(){}

    public static <T> T objectExchange(Object origin , Class<T> target){
        if(Objects.isNull(origin)){
            return null;
        }
        T entityObject = null;
        try {
            entityObject = target.newInstance();
            BeanUtils.copyProperties(origin,entityObject);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return entityObject;
    }

    public static <T> List<T>  listObjectExchange(List<? extends Object> origin , Class<T> target){
        if(CollectionUtils.isEmpty(origin)){
            return null;
        }
        List<T> result = new ArrayList<>(origin.size());

        try {
            for (Object o : origin) {
                T targetObject = target.newInstance();
                BeanUtils.copyProperties(o,targetObject);
                result.add(targetObject);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return result;
    }
}
