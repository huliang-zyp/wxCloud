package com.tencent.wxcloudrun.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@ApiModel
public class OrderInfo implements Serializable {
    private Integer id;
    private String orderName;
    private String orderId;
    private String orderType;
    private Integer orderStatus;
    private String upstreamChannel;
    private String downstreamChannel;
    private String appName;
    private String appid ;
    private String billingMethod ;
    private Date deliveryPeriod;
    private Integer totalDeliveryQuantity;
    private Integer dailyDeliveryLimit;
    private Date deliveryTimeRequirement;
    private Integer sellPrice;
    private Integer buyPrice;
    private Date createdTime;
    private Date updatedTime;
    private Integer todayCompletionCount;
    private Integer totalCompletionCount;
}
