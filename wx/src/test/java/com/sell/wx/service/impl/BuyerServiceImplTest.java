package com.sell.wx.service.impl;

import com.sell.wx.dto.OrderDTO;
import com.sell.wx.enums.OrderStatusEnum;
import com.sell.wx.service.BuyerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BuyerServiceImplTest {
@Autowired
private BuyerService buyerService;
    @Test
    public void findOrderOne() {
       OrderDTO orderDTO= buyerService.findOrderOne("000003","1554985250839993509");
        Assert.assertEquals("000003",orderDTO.getBuyerOpenid());
    }

    @Test
    public void cancelOrder() {
        OrderDTO orderDTO= buyerService.cancelOrder("000015","1555056765255461289");

    }
}