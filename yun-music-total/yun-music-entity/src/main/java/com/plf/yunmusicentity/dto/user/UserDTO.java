package com.plf.yunmusicentity.dto.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

    private String userId;

    private String userCode;

    private String userNickName;

    private Integer userSex;

    private String userIphoneNumber;

    private String userAddress;

    private String userEmail;

    private String userImage;

    private Date userBirthday;

    private String userPassword;

    private Date userCreateDate;

    private Integer userVersion;

    private String token;
}
