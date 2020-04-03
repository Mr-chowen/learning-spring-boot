package com.xust.mall.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class CommonResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int resultCode;

    private String message;

    private T data;

    public CommonResult(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }
}
