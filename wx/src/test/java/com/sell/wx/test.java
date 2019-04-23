package com.sell.wx;

import com.sell.wx.VO.ProductInfoVO;
import com.sell.wx.VO.ProductVO;
import com.sell.wx.dao.ProductInfo;
import com.sell.wx.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
    @Autowired
    private ProductService productService;
    @Test
    public void test(){
        List<ProductInfo> productInfoList=productService.findUpAll();
        for (ProductInfo productInfo:productInfoList){
            ProductInfoVO productInfoVO=new ProductInfoVO();
            BeanUtils.copyProperties(productInfo,productInfoVO);
            System.out.println(productInfoVO);
        }
    }
}
