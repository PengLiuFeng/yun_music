package com.plf.yunmusicentity.dto;/**
 * @author acer
 * @date 2022/6/7 15:39
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: xiongchaoyu
 * @CreateTime: 2022-06-07  15:39
 * @Description: 内部OCR接口返回对象体
 * @Version: 1.0
 */
@Data
public class IdentifyCertificatesResponseVO implements Serializable {

    //返回状态 正确为:ok
    private String status;

    //错误信息
    private String errorMsg;

    //错误码
    private String errorCode;

    private Recognition recognition;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Recognition {
        private List<FieldData> list;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FieldData {
        private String fieldName;
        private FieldDetail fieldDetail;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FieldDetail {
        private String text;
    }
}
