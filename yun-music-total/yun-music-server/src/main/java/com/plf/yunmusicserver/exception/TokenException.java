package com.plf.yunmusicserver.exception;

import java.util.concurrent.ExecutionException;

public class TokenException extends ExecutionException {

    private String exceptionMessage;

    private String code;

    TokenException(){

    }

    public TokenException(String exceptionMessage, String code) {
        this.exceptionMessage = exceptionMessage ;
        this.code = code ;
    }

}
