package com.mu.muses.config;


public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(200, "Request is successful"),
    CREAT(201,"Created successful"),
    FAIL(400, "Request is failed"),
    TOKEN_INVALID(401, "Token is null or invalid"),
    ACCESS_DENIED(403, "Access denied"),
    FAIL_DELETE(501, "Delete failed"),
    FAIL_UPDATE(502, "Update failed");

    private Integer code;
    private String msg;
    ResultCode(Integer code, String message){
        this.code = code;
        this.msg = message;
    }

    public Integer code(){
        return this.code;
    }

    public String message(){
        return this.msg;
    }
}
