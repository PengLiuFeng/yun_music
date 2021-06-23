package com.plf.yunmusicentity.commonhttp;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorInfo<T> {
    private String message;
    private T data;
    private String code;
    private String url;
    private Date date;
}
