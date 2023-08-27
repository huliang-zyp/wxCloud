package com.tencent.wxcloudrun.config;

import com.tencent.wxcloudrun.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // 基于 token，不需要使用 Session 了
                .sessionManagement() // Session 管理
                // 管理 Session 创建策略
                //    ALWAYS, 总是创建HttpSession
                //    NEVER, 只会在需要时创建一个HttpSession
                //    IF_REQUIRED, 不会创建HttpSession，但如果它已经存在，将可以使用HttpSession
                //    STATELESS; 永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/public/**",
                        "favicon.ico",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/configuration/**",
                        "/swagger-ui.html",
                        "/doc.html",
                        "/swagger*/**",
                        "/webjars/**")
                .permitAll() // 授权请求
                // 除了上面的请求，其他所有请求都需要认证
                .anyRequest()
                .authenticated()
                .and()
                // 禁止缓存
                .headers()
                .cacheControl();

                http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

