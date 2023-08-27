package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderInfoMapper {
    List<OrderInfo> findOrderList();
}
