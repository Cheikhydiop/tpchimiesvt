package com.example.projetjavax;

import java.util.HashMap;
import java.util.Map;

public class Session {
    private static Map<String, Object> sessionData=new HashMap<>();

    public static void setAttribute(String key, Object value){
        sessionData.put(key,value);
    }

    public static Object getAttribute(String key){
        return sessionData.get(key);
    }

    public static void removeAttribute(String key){
        sessionData.remove(key);
    }
}
