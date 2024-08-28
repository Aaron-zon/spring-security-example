package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", "aa");
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void TestBCryptPasswordEncoder() {
        String encode1 = passwordEncoder.encode("123456");
        System.out.println(passwordEncoder.matches("123456", encode1));

        String encode2 = passwordEncoder.encode("123456");
        System.out.println(encode1);
        System.out.println(encode2);
    }

}
