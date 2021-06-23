package com.plf.yunmusicserver.handle.exception;

import com.plf.yunmusicentity.commonhttp.ErrorInfo;
import com.plf.yunmusicentity.enums.ResponseStatusEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//@ControllerAdvice
public class ExceptionUnitHandle {

   // @ExceptionHandler(value = Exception.class)
    //@ResponseBody
    public ErrorInfo<Exception> handleException(HttpServletRequest req , Exception e ){
        ErrorInfo<Exception> result = new ErrorInfo<>();
        result.setCode(ResponseStatusEnum.EXCEPTION.getCode());
        result.setDate(new Date());
        result.setData(e);
        result.setMessage(e.getMessage());
        result.setUrl(req.getRequestURL().toString());
        return result;
    }
}
