package com.sell.wx.service.impl;

import com.sell.wx.dao.ProductInfo;
import com.sell.wx.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
@Autowired
private ProductServiceImpl productService;
    @Test
    public void findOne() {
       ProductInfo result=productService.findOne("1");
       Assert.assertEquals("1",result.getProductId());
    }

    @Test
    public void save() {
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("3");
        productInfo.setProductName("炒饭");
        productInfo.setProductPrice(BigDecimal.valueOf(9));
        productInfo.setProductStock(200);
        productInfo.setProductDescription("很潮的嘲讽");
        productInfo.setProductIcon("cf.jpg");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(3);
       ProductInfo result=productService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findUpAll() {
       List<ProductInfo> result= productService.findUpAll();
       Assert.assertNotEquals(1,result.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest=new PageRequest(1,2);
       Page<ProductInfo> productInfoPage= productService.findAll(pageRequest);
       Assert.assertNotEquals(0,productInfoPage.getTotalElements());
    }
}