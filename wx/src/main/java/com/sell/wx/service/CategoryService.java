package com.sell.wx.service;

import com.sell.wx.dao.ProductCategory;

import java.util.List;

public interface CategoryService {
  ProductCategory findOne(Integer categoryId);

  List<ProductCategory> findAll();

  List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryList);

  ProductCategory save(ProductCategory productCategory);
}
