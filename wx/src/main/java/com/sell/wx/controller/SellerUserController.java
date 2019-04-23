package com.sell.wx.controller;

import com.sell.wx.constant.CookieConstant;
import com.sell.wx.constant.RedisConstant;
import com.sell.wx.dao.SellerInfo;
import com.sell.wx.enums.ResultEnum;
import com.sell.wx.service.SellerInfoService;
import com.sell.wx.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerUserController {
    @Autowired
    private SellerInfoService sellerInfoService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Value("http://localhost:8080")
    private String projectUrlConfig;
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                      HttpServletResponse response,
                      Map<String,Object> map){
        /*openid和数据库里的数据匹配*/
       SellerInfo sellerInfo= sellerInfoService.findSellerInfoByOpenid(openid);
       if (sellerInfo==null){
           map.put("msg", ResultEnum.LOGIN_FAIL);
           map.put("url","/sell/seller/order/list");
           return new ModelAndView("/common/error",map);
       }
        /*设置token至redis*/
        String token= UUID.randomUUID().toString();
        Integer expire= RedisConstant.EXPIRE;
         redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,expire, TimeUnit.SECONDS);
        /*设置token至cookie*/
        CookieUtil.set(response, CookieConstant.TOKEN,token,expire);

        return  new ModelAndView("redirect:"+projectUrlConfig+"/sell/seller/order/list");
    }


    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String,Object> map){
        /*从Cookie里查询*/
        Cookie cookie=CookieUtil.get(request,CookieConstant.TOKEN);
        if (cookie!=null){
            /*清除redis*/
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));

            /*清除cookie*/
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }
        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new  ModelAndView("common/success",map);
    }
}
