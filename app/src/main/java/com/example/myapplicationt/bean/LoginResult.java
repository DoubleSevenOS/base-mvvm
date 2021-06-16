package com.example.myapplicationt.bean;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
public class LoginResult {
    @Nullable
    private Integer status;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResult(@Nullable Integer error) {
        this.status = error;
    }

    @Nullable
    public Integer getError() {
        return status;
    }
}