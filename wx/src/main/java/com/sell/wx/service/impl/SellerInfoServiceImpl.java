package com.sell.wx.service.impl;

import com.sell.wx.dao.SellerInfo;
import com.sell.wx.repository.SellerInfoRepository;
import com.sell.wx.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {
    @Autowired
    private SellerInfoRepository repository;
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
