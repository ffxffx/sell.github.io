package com.sell.wx.exception;

import com.sell.wx.enums.ResultEnum;
import lombok.Getter;


@Getter
public class SellException extends RuntimeException{

    private Integer code;


    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }
    public SellException(Integer code,String message) {
        this.code = code;
    }
}
