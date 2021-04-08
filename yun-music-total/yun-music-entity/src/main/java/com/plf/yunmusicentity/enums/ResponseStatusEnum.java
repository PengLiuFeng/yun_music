package com.plf.yunmusicentity.enums;

public enum ResponseStatusEnum {
    SUCCESS("0","请求成功"),
    ERROR("-1","接口调用错误！"),
    HTTP_ERROR("-2","http调用错误！");

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private String code ;
    private String message;

    ResponseStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
