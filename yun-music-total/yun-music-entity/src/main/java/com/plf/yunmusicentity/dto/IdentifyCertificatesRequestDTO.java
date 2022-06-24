/**
 * OPay Inc.
 * Copyright (c) 2016-2022 All Rights Reserved.
 */
package com.plf.yunmusicentity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sufeng.dong
 * @version $Id: CnicPairingRequestDTO.java, v 0.1 2022-05-30 7:34 PM sufeng.dong Exp $$
 */
@Data
public class IdentifyCertificatesRequestDTO implements Serializable {

    /**
     * 手机号
     */
    private Long userId;
    /**
     * 证件号
     */
    private String file;
}
