package com.sell.wx.controller;

import com.sell.wx.VO.ResultVO;
import com.sell.wx.converter.OrderForm2OrderDTOConverter;
import com.sell.wx.dto.OrderDTO;
import com.sell.wx.enums.ResultEnum;
import com.sell.wx.exception.SellException;
import com.sell.wx.form.OrderForm;
import com.sell.wx.service.BuyerService;
import com.sell.wx.service.OrderService;
import com.sell.wx.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }
    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page") Integer page,
                                         @RequestParam(value = "size") Integer size){
        if (StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest=new PageRequest(page, size);
       Page<OrderDTO> orderDTOPage= orderService.findList(pageRequest);
       return ResultVOUtil.success(orderDTOPage.getContent());
    }
    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam(value = "openid") String openId,
                                     @RequestParam(value = "orderId") String orderId
                                     ){
       OrderDTO orderDTO= buyerService.findOrderOne(openId,orderId);
       return ResultVOUtil.success(orderDTO);
    }
    //取消订单
    @GetMapping("/cancel")
    public ResultVO cancel(@RequestParam(value = "openid") String openId,
                           @RequestParam(value = "orderId") String orderId){
        buyerService.cancelOrder(openId, orderId);
        return ResultVOUtil.success();
    }

}
