package com.mu.muses.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RestResponse<T> implements Serializable {

    /*** 请求是否成功  */
    @ApiModelProperty(value = "请求是否成功")
    private boolean success;
    /**  * 具体数据  */
    @ApiModelProperty(value = "具体数据")
    private T data;
    /**  * 错误码  */
    @ApiModelProperty(value = "错误码")
    private Integer code;
    /**  * 错误信息  */
    @ApiModelProperty(value = "错误信息")
    private String msg;


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
        response.setResultCode(ResultCode.FAIL);
        response.setMessage(msg);
        response.setSuccess(false);
        return response;
    }

    public void setData(T data){
        this.data = data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
        return msg;
    }

    public void setMessage(String message){
        this.msg = message;
    }

    private void setResultCode(ResultCode resultCode){
        this.code = resultCode.code();
        this.msg = resultCode.message();
    }

}


