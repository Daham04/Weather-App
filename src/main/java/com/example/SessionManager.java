package com.example;

import java.util.*;

public class SessionManager {

    private static final Map<String, String> sessions = new HashMap<>();

    public static String createSession(String email) {
        String token = UUID.randomUUID().toString();
        sessions.put(token, email);
        return token;
    }

    public static boolean isValid(String token) {
        return sessions.containsKey(token);
    }
}
