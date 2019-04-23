package com.sell.wx.service;

import com.sell.wx.dao.SellerInfo;

public interface SellerInfoService {
   SellerInfo findSellerInfoByOpenid(String openid);
}
