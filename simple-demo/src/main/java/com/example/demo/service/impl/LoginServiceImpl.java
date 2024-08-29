package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.LoginUser;
import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.LoginServcie;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginServcie {
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 实现登录函数逻辑
     * @param user
     * @return
     */
    @Override
    public Map login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        // 执行后默认会调用实现了UserDetailsService接口的类的loadUserByUsername方法
        //认证成功，提供者返回一个填充了用户详细信息和授权信息的新Authentication对象
        //这个对象随后被Spring Security框架接受并存储在SecurityContextHolder中，表示用户当前的认证状态
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //判断当前认证成功、失败, 抛出接口异常;
        if (authentication == null) {
            throw new RuntimeException("用户名或密码错误");
        }

        //使用userid生成token
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String username = loginUser.getUsername();
        String jwt = JwtUtil.createJWT(userId, username, loginUser.getUser(), 3600000);

        System.out.println("jwt: " + jwt);

        // 返回响应
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("token", jwt);
        return map;
    }

    @Override
    public Map logout() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        Long userid = loginUser.getUser().getId();
        System.out.println("userid:" + userid);
        Map<String, Object> map = new HashMap<>();
        map.put("userid", userid);
        return map;
    }
}
