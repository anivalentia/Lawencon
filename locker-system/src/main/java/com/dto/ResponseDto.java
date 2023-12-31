package com.dto;

public class ResponseDto {
    private String status;
    private String message;
    private Object payload;

    public ResponseDto() {
    }

    public ResponseDto(String status, String message, Object payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getPayload() {
        return this.payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
