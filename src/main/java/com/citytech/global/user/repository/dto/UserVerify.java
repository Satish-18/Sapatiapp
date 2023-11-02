package com.citytech.global.user.repository.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public class UserVerify {
    private String adminName;
    private String userName;

    public UserVerify(String adminName, String userName) {
        this.adminName = adminName;
        this.userName = userName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserVerify{" +
                "adminName='" + adminName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public UserVerify() {
    }
}
