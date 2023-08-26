package com.tencent.wxcloudrun.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "AuthenticationRequest",description = "登陆信息")
public class AuthenticationRequest {
    String username;
    String password;
}
