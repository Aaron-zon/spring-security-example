package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.LoginServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginServcie loginServcie;

    @RequestMapping("/login")
    public Map login(@RequestBody User user) {
        try {
            return loginServcie.login(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping("/logout")
    public Map logout() {
        try {
            return loginServcie.logout();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
