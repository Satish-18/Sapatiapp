package com.citytech.global.register.dto.login;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable.Serializable

public class LoginRequest {
    private String firstName;
    private String email;
    private String password;


    public LoginRequest(String firstName, String email, String password) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }


    public void setUserName(String userName) {
        this.firstName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "SignInRequest{" +
                "userName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public LoginRequest() {
    }
}
