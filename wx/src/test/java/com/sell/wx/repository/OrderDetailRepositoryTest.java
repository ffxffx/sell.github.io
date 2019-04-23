package com.sell.wx.repository;

import com.sell.wx.dao.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
@Autowired
private OrderDetailRepository repository;
    @Test
    public void findByOrderId() {
       List<OrderDetail> orderDetailList=repository.findByOrderId("155066937044389129");
        Assert.assertEquals(2,orderDetailList.size());
    }
}