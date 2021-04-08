package com.plf.yunmusicserver.entity;

import java.util.Date;

public class Power {
    private String powerId;

    private String powerCode;

    private String powerName;

    private Date powerCreateDate;

    private String powerCreateUser;

    private Integer powerVersion;

    private String powerDescribe;

    public String getPowerId() {
        return powerId;
    }

    public void setPowerId(String powerId) {
        this.powerId = powerId == null ? null : powerId.trim();
    }

    public String getPowerCode() {
        return powerCode;
    }

    public void setPowerCode(String powerCode) {
        this.powerCode = powerCode == null ? null : powerCode.trim();
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName == null ? null : powerName.trim();
    }

    public Date getPowerCreateDate() {
        return powerCreateDate;
    }

    public void setPowerCreateDate(Date powerCreateDate) {
        this.powerCreateDate = powerCreateDate;
    }

    public String getPowerCreateUser() {
        return powerCreateUser;
    }

    public void setPowerCreateUser(String powerCreateUser) {
        this.powerCreateUser = powerCreateUser == null ? null : powerCreateUser.trim();
    }

    public Integer getPowerVersion() {
        return powerVersion;
    }

    public void setPowerVersion(Integer powerVersion) {
        this.powerVersion = powerVersion;
    }

    public String getPowerDescribe() {
        return powerDescribe;
    }

    public void setPowerDescribe(String powerDescribe) {
        this.powerDescribe = powerDescribe == null ? null : powerDescribe.trim();
    }
}