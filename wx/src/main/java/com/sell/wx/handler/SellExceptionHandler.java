package com.sell.wx.handler;

import com.sell.wx.VO.ResultVO;
import com.sell.wx.exception.SellException;
import com.sell.wx.exception.SellerAuthorizeException;
import com.sell.wx.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class SellExceptionHandler {
    /*拦截登录异常*/
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:"
        .concat("/seller/login"));
    }
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handleSellException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }

}
