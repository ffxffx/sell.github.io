package com.sell.wx.repository;

import com.sell.wx.dao.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo ,String> {
    SellerInfo findByOpenid(String openid);
}
