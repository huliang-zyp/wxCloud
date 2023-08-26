package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    String username;
    String password;
}
