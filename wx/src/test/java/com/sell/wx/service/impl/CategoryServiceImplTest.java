package com.sell.wx.service.impl;

import com.sell.wx.dao.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
@Autowired
private CategoryServiceImpl categoryService;
    @Test
    public void findOne() {
     ProductCategory result= categoryService.findOne(2);
        Assert.assertEquals(new Integer(2),result.getCategoryId());
    }

    @Test
    public void findAll() {
     List<ProductCategory> categoryList= categoryService.findAll();
     Assert.assertNotEquals(0,categoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() {
      List<ProductCategory> categoryList= categoryService.findByCategoryTypeIn(Arrays.asList(2,3,4));
        Assert.assertNotEquals(0,categoryList);
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("男生专享", 10);
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}