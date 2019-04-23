package com.sell.wx.controller;

import com.sell.wx.VO.ProductInfoVO;
import com.sell.wx.VO.ProductVO;
import com.sell.wx.VO.ResultVO;
import com.sell.wx.dao.ProductCategory;
import com.sell.wx.dao.ProductInfo;
import com.sell.wx.service.CategoryService;
import com.sell.wx.service.ProductService;
import com.sell.wx.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600*60)
@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ResultVO list(){
        //1.查询所有的上架商品
        List<ProductInfo>  productInfoList=productService.findUpAll();

        //2. 查询类目(一次性查询)
      List<Integer> categoryTpeList=new ArrayList<>();
      for (ProductInfo productInfo: productInfoList){
          categoryTpeList.add(productInfo.getCategoryType());
      }
        List<ProductCategory> productCategoryList=categoryService.findByCategoryTypeIn(categoryTpeList);
      //数据拼装

        List<ProductVO> productVOList=new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList){
            ProductVO productVO=new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo:productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO=new ProductInfoVO();
//                    productInfoVO.setProductId(productInfo.getProductId());
//                    productInfoVO.setProductName(productInfo.getProductName());
//                    productInfoVO.setPrice(productInfo.getProductPrice());
//                    productInfoVO.setDescription(productInfo.getProductDescription());
//                    productInfoVO.setIcon(productInfo.getProductIcon());
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
