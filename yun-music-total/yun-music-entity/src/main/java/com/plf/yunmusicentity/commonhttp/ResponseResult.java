package com.plf.yunmusicentity.commonhttp;

import com.plf.yunmusicentity.enums.ResponseStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult<T> implements Serializable {

     @ApiModelProperty(value = "返回结果")
     private T date ;

     @ApiModelProperty(value = "接口返回code")
     private String code;

     @ApiModelProperty(value = "接口返回消息提示，error的时候返回")
     private String message;

     @ApiModelProperty(value = "返回结果")
     private boolean isSuccess;

     @ApiModelProperty(value = "返回结果")
     private boolean isFail;
     
     public static <T> ResponseResult<T> getResponseBody(){
          return new ResponseResult();
     }

     public ResponseResult setBaseInfo(String code , String message){
          this.code = code;
          this.message = message;
          return this;
     }

     public ResponseResult setBaseInfo(T date , String code , String message){
          this.date = date;
          this.code = code;
          this.message = message;
          return this;
     }

     public ResponseResult(String code , String message){
          this.code = code;
          this.message = message;
     }

     public ResponseResult(){
          this.code = ResponseStatusEnum.ERROR.getCode();
          this.message = ResponseStatusEnum.ERROR.getMessage();
     }

     public ResponseResult(ResponseStatusEnum responseStatusEnum){
          this.code = responseStatusEnum.getCode();
          this.message = responseStatusEnum.getMessage();
     }

     public ResponseResult(T date , String code , String message){
          this.date = date;
          this.code = code;
          this.message = message;
     }

     public ResponseResult(T date , ResponseStatusEnum responseStatusEnum){
          this.date = date;
          this.code = responseStatusEnum.getCode();
          this.message = responseStatusEnum.getMessage();
     }


     public static <T> ResponseResult<T> success(){
         return new ResponseResult(ResponseStatusEnum.SUCCESS);
     }

     public static <T> ResponseResult<T> success(T date){
          return new ResponseResult(date , ResponseStatusEnum.SUCCESS);
     }

     public static ResponseResult error(String code , String message){
          return getResponseBody().setBaseInfo(code,message);
     }

     public static <T> ResponseResult<T> error(T date , String code , String message){
          return getResponseBody().setBaseInfo(date ,code,message);
     }
}
