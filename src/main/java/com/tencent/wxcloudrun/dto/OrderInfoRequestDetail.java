package com.tencent.wxcloudrun.dto;

import com.tencent.wxcloudrun.model.UserPropertiesInfo;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class OrderInfoRequestDetail extends OrderInfoRequest{
    @ApiModelProperty(value = "订单名称")
    private String orderName;
    @ApiModelProperty(value = "上游渠道号")
    private String upstreamChannel;
    @ApiModelProperty(value = "公众号ID")
    private String appid ;
    @ApiModelProperty(value = "结算方式")
    private String billingMethod ;
    @ApiModelProperty(value = "投放周期开始")
    private Date deliveryPeriodStart;
    @ApiModelProperty(value = "投放周期结束")
    private Date deliveryPeriodEnd;
    @ApiModelProperty(value = "投放总量")
    private Integer totalDeliveryQuantity;
    @ApiModelProperty(value = "每日投放上限")
    private Integer dailyDeliveryLimit;
    @ApiModelProperty(value = "投放时间限制：0-不限制，1-仅工作日投放，2-周末投放，3-隔日投放")
    private Integer deliveryTimeRequirementType;
    @ApiModelProperty(value = "是否有一日投放时间限制：0-不限制，1-有限制")
    private Integer dailyDeliveryLimitTimeRequirement;
    @ApiModelProperty(value = "一日投放开始时间")
    private Date dailyDeliveryLimitTimeStart;
    @ApiModelProperty(value = "一日投放结束时间")
    private Date dailyDeliveryLimitTimeEnd;
    @ApiModelProperty(value = "投放售价")
    private Integer sellPrice;
    @ApiModelProperty(value = "成本价格")
    private Integer buyPrice;
    @ApiModelProperty(value = "今日完成数")
    private Integer todayCompletionCount;
    @ApiModelProperty(value = "总完成数")
    private Integer totalCompletionCount;
    @ApiModelProperty(value = "优先级")
    private Integer priority;
    @ApiModelProperty(value = "定投属性")
    private UserPropertiesInfo userPropertiesInfo;
    private Date createdTime;
    private Date updatedTime;
}
