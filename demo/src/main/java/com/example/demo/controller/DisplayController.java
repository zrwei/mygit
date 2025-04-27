package com.example.demo.controller;

import com.example.demo.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DisplayController {

    @GetMapping("/display")
    public Map<String, Object> displayPage(@RequestHeader("Authorization") String token) {
        Map<String, Object> result = new HashMap<>();
        token = token.replace("Bearer ", "");
        if (JwtUtil.validateToken(token)) {
            String username = JwtUtil.getUsernameFromToken(token);
            result.put("success", true);
            result.put("message", "Welcome to the display page, " + username);
        } else {
            result.put("success", false);
            result.put("message", "Invalid token. Please log in again.");
        }
        return result;
    }
}    