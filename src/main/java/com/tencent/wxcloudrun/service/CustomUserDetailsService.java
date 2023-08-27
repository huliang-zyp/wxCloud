package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dao.UserMapper;
import com.tencent.wxcloudrun.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    final UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(@Autowired UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        // 临时写死 这里是数据库查询出来的
        return org.springframework.security.core.userdetails.User.builder().username("huliang")
                .password(passwordEncoder.encode("123456"))
                .authorities(authorities).build();

//        User user = userMapper.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                true,
//                user.isEnabled(),
//                true, true,
//                Collections.emptyList()
//        );
//        if (userDetails==null || !passwordEncoder.matches(password,userDetails.getPassword())){
//            return RespBean.error("用户名或密码错误！");
//        }
//        if (!userDetails.isEnabled()){
//            return RespBean.error("用户状态异常！");
//        }
//
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
//        String jwt = jwtUtil.foundJWT(userDetails);
//        SecurityContextHolder.getContext().setAuthentication(token);
//        Map<String,String> msg = new HashMap<>();
//        msg.put("tokenHead",tokenHead);
//        msg.put("token", jwt);
//        return RespBean.success("登录成功！",msg);
    }
}

