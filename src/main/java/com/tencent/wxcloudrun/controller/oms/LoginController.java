package com.tencent.wxcloudrun.controller.oms;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.JwtUtil;
import com.tencent.wxcloudrun.dto.AuthenticationRequest;
import com.tencent.wxcloudrun.service.CustomUserDetailsService;
import com.tencent.wxcloudrun.service.JwtAuthService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;

@RestController
public class LoginController {
    final JwtAuthService jwtAuthService;
    final Logger logger;
    public LoginController(@Autowired JwtAuthService jwtAuthService) {
        this.jwtAuthService = jwtAuthService;
        this.logger = LoggerFactory.getLogger(LoginController.class);
    }

    @ApiOperation("用户登陆鉴权")
    @PostMapping(value = "/public/login")
    public ApiResponse login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        String token = jwtAuthService.login(authenticationRequest.getUsername(),authenticationRequest.getPassword());
        return ApiResponse.ok(token);
    }
}
