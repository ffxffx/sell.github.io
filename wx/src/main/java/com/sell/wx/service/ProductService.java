package com.sell.wx.service;

import com.sell.wx.dao.ProductInfo;
import com.sell.wx.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
   ProductInfo findOne(String productId);

   ProductInfo save(ProductInfo productInfo);

    /**
     * 查询所有上架商品
     * @return
     */
  List<ProductInfo> findUpAll();
    /*
    *分页查询所有
    */
  Page<ProductInfo> findAll(Pageable pageable);

  //加库存
    void increaseStock(List<CartDTO> cartDTOList);
    // 减库存
    void decreaseStock(List<CartDTO> cartDTOList);
    //上架
    ProductInfo onSale(String productId);
    //下架
    ProductInfo offSale(String productId);
}
