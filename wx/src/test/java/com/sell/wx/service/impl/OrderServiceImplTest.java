package com.sell.wx.service.impl;

import com.sell.wx.dao.OrderDetail;
import com.sell.wx.dto.OrderDTO;
import com.sell.wx.enums.OrderStatusEnum;
import com.sell.wx.enums.PayStatusEnum;
import com.sell.wx.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;
private final String BUYER_OPENID = "000001";
    private final String ORDER_ID = "1554880252706136508";
    @Test
    public void create() {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("张十七");
        orderDTO.setBuyerAddress("温州");
        orderDTO.setBuyerPhone("121452145227");
        orderDTO.setBuyerOpenid("000017");

        List<OrderDetail> orderDetailList=new ArrayList<>();
//        OrderDetail o1=new OrderDetail();
//        o1.setProductId("3");
//        o1.setProductQuantity(5);

        OrderDetail o2=new OrderDetail();
        o2.setProductId("2");
        o2.setProductQuantity(2);
        //orderDetailList.add(o1);
        orderDetailList.add(o2);
        orderDTO.setOrderDetailList(orderDetailList);

       OrderDTO result= orderService.create(orderDTO);
        log.info("【查询单个订单】result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
       OrderDTO orderDTO= orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】result={}", orderDTO);
       Assert.assertEquals(ORDER_ID,orderDTO.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest pageRequest=new PageRequest(0,2);
       Page<OrderDTO>orderDTOPage= orderService.findList(BUYER_OPENID,pageRequest);
       Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO=orderService.findOne(ORDER_ID);
        OrderDTO result=orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO=orderService.findOne(ORDER_ID);
        OrderDTO result=orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO=orderService.findOne(ORDER_ID);
        OrderDTO result=orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }

    @Test
    public void findList1() {
        PageRequest pageRequest=new PageRequest(0,10);
        Page<OrderDTO> orderDTOPage= orderService.findList(pageRequest);
        Assert.assertTrue("查询所有的订单列表", orderDTOPage.getTotalElements() > 0);
    }
}