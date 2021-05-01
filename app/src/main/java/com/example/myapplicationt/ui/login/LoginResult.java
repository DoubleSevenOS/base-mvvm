package com.example.myapplicationt.ui.login;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer status;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    LoginResult(@Nullable Integer error) {
        this.status = error;
    }

    @Nullable
    Integer getError() {
        return status;
    }
}