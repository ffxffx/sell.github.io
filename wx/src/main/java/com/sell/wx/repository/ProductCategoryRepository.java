package com.sell.wx.repository;

import com.sell.wx.dao.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    /*根据CategoryType查询ProductCategory*/
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
