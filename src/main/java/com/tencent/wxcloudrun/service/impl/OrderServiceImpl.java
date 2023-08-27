package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.CountersMapper;
import com.tencent.wxcloudrun.dao.OrderInfoMapper;
import com.tencent.wxcloudrun.dto.OrderInfoRequest;
import com.tencent.wxcloudrun.model.OrderInfo;
import com.tencent.wxcloudrun.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    final OrderInfoMapper orderInfoMapper;
    final Logger logger;
    public OrderServiceImpl(@Autowired OrderInfoMapper orderInfoMapper) {
        this.orderInfoMapper = orderInfoMapper;
        this.logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    }
    @Override
    public List<OrderInfo> getOrderInfoList(OrderInfoRequest orderInfoRequest) {
        List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
        BeanUtils.copyProperties(orderInfoRequest, orderInfoList);
        orderInfoList = orderInfoMapper.findOrderList();
        return orderInfoList;
    }
}
