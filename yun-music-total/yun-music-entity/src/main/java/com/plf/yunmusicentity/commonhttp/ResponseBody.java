package com.plf.yunmusicentity.commonhttp;

import com.plf.yunmusicentity.enums.ResponseStatusEnum;

import java.io.Serializable;

public class ResponseBody<T> implements Serializable {

     private T date ;

     private String code;

     private String message;

     private boolean isSuccess;

     private boolean isFail;
     
     public static ResponseBody getResponseBody(){
          return new ResponseBody();
     }

     public ResponseBody setBaseInfo(String code , String message){
          this.code = code;
          this.message = message;
          return this;
     }

     public ResponseBody setBaseInfo(T date , String code , String message){
          this.date = date;
          this.code = code;
          this.message = message;
          return this;
     }

     public ResponseBody(String code , String message){
          this.code = code;
          this.message = message;
     }

     public ResponseBody(){
          this.code = ResponseStatusEnum.ERROR.getCode();
          this.message = ResponseStatusEnum.ERROR.getMessage();
     }

     public ResponseBody(ResponseStatusEnum responseStatusEnum){
          this.code = responseStatusEnum.getCode();
          this.message = responseStatusEnum.getMessage();
     }

     public ResponseBody(T date , String code , String message){
          this.date = date;
          this.code = code;
          this.message = message;
     }

     public ResponseBody(T date , ResponseStatusEnum responseStatusEnum){
          this.date = date;
          this.code = responseStatusEnum.getCode();
          this.message = responseStatusEnum.getMessage();
     }


     public static <T> ResponseBody<T> success(){
         return new ResponseBody(ResponseStatusEnum.SUCCESS);
     }

     public static <T> ResponseBody<T> success(T date){
          return new ResponseBody(date , ResponseStatusEnum.SUCCESS);
     }

     public static <T> ResponseBody<T> error(String code , String message){
          return getResponseBody().setBaseInfo(code,message);
     }

     public static <T> ResponseBody<T> error(T date ,String code , String message){
          return getResponseBody().setBaseInfo(date ,code,message);
     }
}
