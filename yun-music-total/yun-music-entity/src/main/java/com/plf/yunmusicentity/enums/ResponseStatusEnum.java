package com.plf.yunmusicentity.enums;

public enum ResponseStatusEnum {
    SUCCESS("0", "请求成功"),
    ERROR("-1", "接口调用错误！"),
    LOGIN_ERROR("-3", "用户登录号和密码都不能为空！"),
    EXCEPTION("-5","接口调用异常：异常信息：{%s}"),
    HTTP_ERROR("-2", "http调用错误！"),
    MQ_RECEIVE_ERROR("-4","mq消费错误"),
    MQ_SEND_ERROR("-6","MQ推送错误"),
    ERROR001("ERROR001","账号或者密码错误，请确认后重试"),
    ERROR002("ERROR002","当前无法登录，请稍后再试！");

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private String code;
    private String message;

    ResponseStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
