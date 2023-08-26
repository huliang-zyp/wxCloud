package com.tencent.wxcloudrun.controller.oms;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.JwtUtil;
import com.tencent.wxcloudrun.dto.AuthenticationRequest;
import com.tencent.wxcloudrun.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private CustomUserDetailsService userDetailsService;
    @PostMapping("/authenticate")
    public ApiResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ApiResponse.ok(jwt);
    }
}
