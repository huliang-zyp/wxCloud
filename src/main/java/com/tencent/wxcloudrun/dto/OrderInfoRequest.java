package com.tencent.wxcloudrun.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class OrderInfoRequest {
    @ApiModelProperty(value = "订单状态")
    private Integer OrderStatus;
    @ApiModelProperty(value = "公众号名称")
    private String appName;
    @ApiModelProperty(value = "订单Id")
    private Integer Id;
    @ApiModelProperty(value = "订单类型")
    private String OrderType;
    @ApiModelProperty(value = "下游渠道号")
    private String downstreamChannel;
}
