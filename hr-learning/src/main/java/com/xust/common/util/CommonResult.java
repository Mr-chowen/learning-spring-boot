package com.xust.common.util;



public class CommonResult {
    private Integer status;

    private String message;

    private Object obj;

    public static CommonResult build(){
        return  new CommonResult();
    }

    public static CommonResult ok(String message){
        return new CommonResult(200,message,null);
    }

    public static CommonResult ok(String message, Object object){
        return new CommonResult(200,message,object);
    }

    public static CommonResult error(String message){
        return new CommonResult(500,message,null);
    }

    public static CommonResult error(String message, Object object){
        return new CommonResult(500,message,object);
    }

    private CommonResult() {
    }

    private CommonResult(Integer status, String message, Object obj) {
        this.status = status;
        this.message = message;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public CommonResult setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public CommonResult setObj(Object obj) {
        this.obj = obj;
        return this;
    }
}
