package com.example.administrator.courcemanager.vo;

/**
 * 用户类
 */
public class RoleInfo {
    String Number;
    String Password;
    String roleInfo;

    public RoleInfo(String number, String password, String roleInfo) {
        Number = number;
        Password = password;
        this.roleInfo = roleInfo;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(String roleInfo) {
        this.roleInfo = roleInfo;
    }
}
