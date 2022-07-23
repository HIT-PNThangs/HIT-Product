package com.example.hit.nhom5.product.model;

public class GetUserByEmailResponse {
    private Integer status;
    private String message;
    private User result;

    public GetUserByEmailResponse() {
    }

    public GetUserByEmailResponse(Integer status, String message, User result) {
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

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }
}
