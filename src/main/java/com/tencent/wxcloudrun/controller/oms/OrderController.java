package com.tencent.wxcloudrun.controller.oms;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.OrderInfoRequest;
import com.tencent.wxcloudrun.model.OrderInfo;
import com.tencent.wxcloudrun.service.JwtAuthService;
import com.tencent.wxcloudrun.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/oms")
public class OrderController {
    final OrderService orderService;
    final Logger logger;
    public OrderController(@Autowired OrderService orderService) {
        this.orderService = orderService;
        this.logger = LoggerFactory.getLogger(LoginController.class);
    }
    @ApiOperation(value = "获取订单列表",notes = "返回订单")
    @PostMapping(value = "/getOrderInfoList")
    public ApiResponse getOrderInfoList(OrderInfoRequest orderInfoRequest){
        List<OrderInfo> orderInfoList = orderService.getOrderInfoList(orderInfoRequest);
        return ApiResponse.ok(orderInfoList);
    }
}
