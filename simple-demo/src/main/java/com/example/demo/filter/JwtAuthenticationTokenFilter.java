package com.example.demo.filter;

import com.example.demo.domain.LoginUser;
import com.example.demo.domain.User;
import com.example.demo.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    /**
     * 请求头Token过滤器
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = httpServletRequest.getHeader("token");
        // 判断是否存在token，不存在放行交给下一个过滤器
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        // 存在Token时解析Token
        User user;
        try {
            Jws<Claims> claims = JwtUtil.pareseJWT(token);
            Map<String, Object> claimsUser = (HashMap) claims.getBody().get("user");
            user = new User(claimsUser);
        } catch (Exception e) {
            // 当Token不正确时抛出异常
            e.printStackTrace();
            throw new RuntimeException("token 非法");
        }

        // Token中不存在用户信息抛出异常
        if (user == null) {
            throw new RuntimeException("用户未登录");
        }

        // 获取权限信息封装到Authentication中 存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(new LoginUser(user), null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // JWT过滤器结束，放行到下一个过滤器
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
