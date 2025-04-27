package com.example.demo.utils;

import java.util.HashSet;
import java.util.Set;

public class TokenBlacklistService {
    private static final Set<String> blacklist = new HashSet<>();

    public static void addToBlacklist(String token) {
        blacklist.add(token);
    }

    public static boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }
}
