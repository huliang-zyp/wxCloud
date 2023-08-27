package com.tencent.wxcloudrun.config;

import com.tencent.wxcloudrun.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Service
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 获取请求头中的指定的值
        String headerToken = request.getHeader("Authorization");
        String tokenHead = "Bearer";
        // 保证 header中的 token 不为 null，且以指定字串开头——Bearer
        if (headerToken != null && headerToken.startsWith(tokenHead)) {
            // 截取有效 token
            String jwtToken = headerToken.substring(tokenHead.length());
            String username = jwtUtil.extractUsername(jwtToken);
            // 判断 UserDetails 中的用户主体是否为null
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // SecurityContextHolder.getContext().getAuthentication() == null 代表着此时 Security 中没有登录的用户主体
                // 此时可以使用有效地 jwtToken 进行用户认证
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 判断 token 是否有效
                if (jwtUtil.validateToken(jwtToken, userDetails)) {
                    // 如果有效则使用 token 中的信息进行登录
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    // 根据请求设置 Details，包含了部分请求信息和主体信息。具体效果不清楚...坑
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // 将 authenticationToken 设置到 SecurityContext 中
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }
            }

        }
        chain.doFilter(request,response);
    }
//
//
//        String username = null;
//        String jwt = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwt = authorizationHeader.substring(7);
//            try {
//                username = jwtUtil.extractUsername(jwt);
//            } catch (ExpiredJwtException e) {
//                // Handle expired token
//
//            }
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//            if (jwtUtil.validateToken(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities()
//                );
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//
//        chain.doFilter(request, response);
//    }
}

