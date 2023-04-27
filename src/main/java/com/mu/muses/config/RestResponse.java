package com.mu.muses.config;

import com.mu.muses.enums.ResultCode;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class RestResponse<T> implements Serializable {

    @ApiModelProperty(value = "请求是否成功")
    private boolean success;
    @ApiModelProperty(value = "具体数据")
    private T data;
    @ApiModelProperty(value = "错误码")
    private Integer code;
    @ApiModelProperty(value = "错误信息")
    private String message;


    public static <T> RestResponse success(){
        RestResponse response = new RestResponse();
        response.setResultCode(ResultCode.SUCCESS);
        response.setSuccess(true);
        return response;
    }

    public static <T> RestResponse success(T data){
        RestResponse response = new RestResponse();
        response.setResultCode(ResultCode.SUCCESS);
        response.setData(data);
        response.setSuccess(true);
        return response;
    }

    public  static <T> RestResponse fail(){
        RestResponse response = new RestResponse();
        response.setResultCode(ResultCode.FAIL);
        response.setSuccess(false);
        response.setData("");
        return response;
    }

    public  static <T> RestResponse fail(ResultCode resultCode){
        RestResponse response = new RestResponse();
        response.setResultCode(resultCode);
        response.setSuccess(false);
        return response;
    }

    public  static <T> RestResponse fail(String msg){
        RestResponse response = new RestResponse();
        response.setSuccess(false);
        response.setResultCode(ResultCode.FAIL);
        response.setData("");
        response.setMessage(msg);

        return response;
    }

    public static  <T> RestResponse fail(String code,String message){
        RestResponse response = new RestResponse();
        response.setCode(Integer.parseInt(code));
        response.setMessage(message);
        response.setSuccess(false);
        response.setData("");
        return response;
    }

    public void setData(T data){
        this.data = data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getData(){
        return data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message){
        this.message= message;
    }

    private void setResultCode(ResultCode resultCode){
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

}


