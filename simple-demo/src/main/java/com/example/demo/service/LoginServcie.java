package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.domain.User;

import java.util.Map;

public interface LoginServcie extends IService<User> {
    public Map login(User user);
}
