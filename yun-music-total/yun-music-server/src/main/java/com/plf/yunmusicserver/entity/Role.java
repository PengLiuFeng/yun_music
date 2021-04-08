package com.plf.yunmusicserver.entity;

import java.util.Date;

public class Role {
    private String roleId;

    private Integer roleNo;

    private String roleCode;

    private String roleName;

    private Date roleCreateDate;

    private String roleCreateUser;

    private Integer roleVersion;

    private String roleDescribe;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public Integer getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(Integer roleNo) {
        this.roleNo = roleNo;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getRoleCreateDate() {
        return roleCreateDate;
    }

    public void setRoleCreateDate(Date roleCreateDate) {
        this.roleCreateDate = roleCreateDate;
    }

    public String getRoleCreateUser() {
        return roleCreateUser;
    }

    public void setRoleCreateUser(String roleCreateUser) {
        this.roleCreateUser = roleCreateUser == null ? null : roleCreateUser.trim();
    }

    public Integer getRoleVersion() {
        return roleVersion;
    }

    public void setRoleVersion(Integer roleVersion) {
        this.roleVersion = roleVersion;
    }

    public String getRoleDescribe() {
        return roleDescribe;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe == null ? null : roleDescribe.trim();
    }
}