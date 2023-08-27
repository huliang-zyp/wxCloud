package com.tencent.wxcloudrun.controller.oms;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.AuthenticationRequest;
import com.tencent.wxcloudrun.service.JwtAuthService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    final JwtAuthService jwtAuthService;
    final Logger logger;
    public LoginController(@Autowired JwtAuthService jwtAuthService) {
        this.jwtAuthService = jwtAuthService;
        this.logger = LoggerFactory.getLogger(LoginController.class);
    }

    @ApiOperation(value = "用户登陆鉴权",notes = "返回token")
    @PostMapping(value = "/public/login")
    public ApiResponse login(@RequestBody AuthenticationRequest authenticationRequest){
        String token = null;
        try {
            token = jwtAuthService.login(authenticationRequest.getUsername(),authenticationRequest.getPassword());
        } catch (Exception e) {
            if(e instanceof UsernameNotFoundException){
                ApiResponse.error("账号密码错误");
            }
        }
        return ApiResponse.ok(token);
    }
}
