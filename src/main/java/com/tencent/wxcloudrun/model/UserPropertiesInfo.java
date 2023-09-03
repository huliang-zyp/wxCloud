package com.tencent.wxcloudrun.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class UserPropertiesInfo {
    @ApiModelProperty(value = "定投性别：1-男，2-女，0-不限制")
    private Integer sex;
    @ApiModelProperty(value = "定投区域")
    private List<AreaInfo> areaInfoList;
}
