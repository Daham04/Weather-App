package com.example;

import com.example.WeatherResult;
import java.util.List;

public class CacheManager {

    private static List<com.example.WeatherResult> cacheData = null;
    private static long lastFetchTime = 0;

    public static boolean isCacheValid(){
        long now = System.currentTimeMillis();
        return cacheData != null && (now - lastFetchTime) < 5*60*1000;
    }

    public static List<WeatherResult> getCacheData(){
        return cacheData;
    }

    public static void setCacheData(List<WeatherResult> data){
        cacheData = data;
        lastFetchTime = System.currentTimeMillis();
    }
}
