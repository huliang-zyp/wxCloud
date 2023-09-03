package com.tencent.wxcloudrun.controller.oms;

import com.tencent.wxcloudrun.config.ApiResponseModel;
import com.tencent.wxcloudrun.dto.OrderInfoRequest;
import com.tencent.wxcloudrun.dto.OrderInfoRequestDetail;
import com.tencent.wxcloudrun.model.OrderInfo;
import com.tencent.wxcloudrun.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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
    public ApiResponseModel<List<OrderInfo>> getOrderInfoList(OrderInfoRequest orderInfoRequest){
        List<OrderInfo> orderInfoList = orderService.getOrderInfoList(orderInfoRequest);
        return ApiResponseModel.ok(orderInfoList);
    }

    public ApiResponseModel CreateOrUpdateOrder(OrderInfoRequestDetail orderInfoRequestDetail){
        ApiResponseModel responseModel = orderService.CreateOrUpdateOrder(orderInfoRequestDetail);
        return responseModel;
    };
}
