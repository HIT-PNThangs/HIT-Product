package com.example.hit.nhom5.product.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("status")
    private Integer status;

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private LoginDTO result;

    public LoginResponse() {
    }

    public LoginResponse(Integer status, String message, LoginDTO result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginDTO getResult() {
        return result;
    }

    public void setResult(LoginDTO result) {
        this.result = result;
    }
}
