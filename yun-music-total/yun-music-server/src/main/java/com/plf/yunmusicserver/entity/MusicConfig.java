package com.plf.yunmusicserver.entity;

import java.util.Date;

public class MusicConfig {
    private String id;

    private Integer typeEnum;

    private String type;

    private Date version;

    private String cofingName;

    private Integer configEnum;

    private String describe;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(Integer typeEnum) {
        this.typeEnum = typeEnum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    public String getCofingName() {
        return cofingName;
    }

    public void setCofingName(String cofingName) {
        this.cofingName = cofingName == null ? null : cofingName.trim();
    }

    public Integer getConfigEnum() {
        return configEnum;
    }

    public void setConfigEnum(Integer configEnum) {
        this.configEnum = configEnum;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }
}