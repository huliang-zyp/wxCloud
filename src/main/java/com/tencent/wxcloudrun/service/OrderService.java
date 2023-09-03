package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.config.ApiResponseModel;
import com.tencent.wxcloudrun.dto.OrderInfoRequest;
import com.tencent.wxcloudrun.dto.OrderInfoRequestDetail;
import com.tencent.wxcloudrun.model.OrderInfo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {

    List<OrderInfo> getOrderInfoList(OrderInfoRequest orderInfoRequest);

    ApiResponseModel CreateOrUpdateOrder(OrderInfoRequestDetail orderInfoRequestDetail);
}
