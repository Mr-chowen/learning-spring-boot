package com.xust.blog.common;

import lombok.Data;
import java.io.Serializable;

@Data
public class CommonResult implements Serializable {
    private Integer code;
    private String message;
    private Object data;

    public CommonResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CommonResult success(int code, String message, Object data) {
        return new CommonResult(code,message,data);
    }

    public static CommonResult success(Object data) {
        return success(200,"success",data);
    }


    public static CommonResult fail(int code, String message, Object data) {
        return new CommonResult(code,message,data);
    }

    public static CommonResult fail(String message) {
        return fail(400,message,null);
    }

}
