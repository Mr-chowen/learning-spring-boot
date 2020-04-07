package com.xust.mall.common;


import java.io.Serializable;

public class CommonResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int resultCode;

    private String message;

    private T data;

    public CommonResult() {
    }

    public CommonResult(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
