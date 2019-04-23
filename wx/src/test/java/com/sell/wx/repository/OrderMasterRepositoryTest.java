package com.sell.wx.repository;

import com.sell.wx.dao.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
   @Autowired
   private OrderMasterRepository repository;
    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest=new PageRequest(1,1);
       Page<OrderMaster> orderMasterPage =repository.findByBuyerOpenid("ffx123456",pageRequest);
        Assert.assertNotEquals(0,orderMasterPage.getTotalElements());
    }
}