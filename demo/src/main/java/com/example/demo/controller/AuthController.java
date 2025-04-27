package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> result = new HashMap<>();
        User user = userService.findUserByUsernameAndPassword(username, password);
        if (user != null) {
            String token = JwtUtil.generateToken(username);
            result.put("success", true);
            result.put("token", token);
        } else {
            result.put("success", false);
            result.put("message", "用户名或密码无效");
        }
        return result;
    }

    @PostMapping("/protected")
    public Map<String, Object> protectedResource(@RequestParam String token) {
        Map<String, Object> result = new HashMap<>();
        if (JwtUtil.validateToken(token)) {
            String username = JwtUtil.getUsernameFromToken(token);
            result.put("success", true);
            result.put("message", "Welcome, " + username);
        } else {
            result.put("success", false);
            result.put("message", "Invalid token");
        }
        return result;
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(@RequestParam String token) {
        Map<String, Object> result = new HashMap<>();
        if (JwtUtil.validateToken(token)) {
            // 这里可以添加将 token 加入黑名单等操作来销毁身份
            // 简单示例中，仅返回成功信息
            result.put("success", true);
            result.put("message", "Logout successful");
        } else {
            result.put("success", false);
            result.put("message", "Invalid token");
        }
        return result;
    }
}