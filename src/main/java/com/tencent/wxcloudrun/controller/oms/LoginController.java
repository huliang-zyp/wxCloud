package com.tencent.wxcloudrun.controller.oms;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.JwtUtil;
import com.tencent.wxcloudrun.dto.AuthenticationRequest;
import com.tencent.wxcloudrun.service.CustomUserDetailsService;
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
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    final Logger logger;
    public LoginController() {
        this.logger = LoggerFactory.getLogger(LoginController.class);
    }
    @ApiOperation("用户登陆鉴权")
    @PostMapping(value = "/public/authenticate")
    public ApiResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        logger.info("进入authenticate");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        logger.info("查询账号信息入参：",JSONObject.toJSONString(authenticationRequest));
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        logger.info("查询账号信息结果：",JSONObject.toJSONString(userDetails));
        final String jwt = jwtUtil.generateToken(userDetails);

        return ApiResponse.ok(jwt);
    }
}
