package com.plf.yunmusicentity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xiongchaoyu
 * @CreateTime: 2022-06-07  11:37
 * @Description: 内部OCR识别返回体对象
 * @Version: 1.0
 */
@Data
public class IdentifyCertificatesResponseDTO implements Serializable {

    //身份证号 对应为 cnic
    private String idCardNo;

    //名字 即对应身份证的name
    private String firstName;

    //姓——即身份证的Father Name
    private String lastName;

    //性别
    private Integer gender;

}
