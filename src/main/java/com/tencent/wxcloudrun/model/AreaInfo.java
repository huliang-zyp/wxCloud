package com.tencent.wxcloudrun.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class AreaInfo {
    @ApiModelProperty(value = "区域代码")
    private String AreaCode;
    @ApiModelProperty(value = "区域名称")
    private String AreaName;
    @ApiModelProperty(value = "区域等级")
    private Integer AreaLevel;
    @ApiModelProperty(value = "区域等级名称")

    private String AreaLevelName;
}
