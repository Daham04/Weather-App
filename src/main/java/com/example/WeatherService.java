package com.example;

import com.example.CacheManager;
import com.example.CityLoader;
import com.example.ComfortCalculator;
import com.example.WeatherResult;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherService {

private static final String API_KEY = "c7f49e239a9deebd02bf5bd4fdcec9d0";

public static List<WeatherResult> getWeatherData() throws Exception{

    if(CacheManager.isCacheValid()){
        return CacheManager.getCacheData();
    }

    List<String> cityCodes = CityLoader.loadCityCodes();
    List<WeatherResult> results = new ArrayList<>();

    for(String cityId : cityCodes){
        String urlStr = "https://api.openweathermap.org/data/2.5/weather?id="
                + cityId + "&appid=" + API_KEY + "&units=metric";

        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        BufferedReader response = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseData = new StringBuilder();
        String line;

        while ((line = response.readLine()) != null) {
            responseData.append(line);
        }

        JsonObject json = new JsonParser().parseString(responseData.toString()).getAsJsonObject();

        String cityName = json.get("name").getAsString();
        double temperature = json.getAsJsonObject("main").get("temp").getAsDouble();
        int humidity = json.getAsJsonObject("main").get("humidity").getAsInt();
        double wind = json.getAsJsonObject("wind").get("speed").getAsDouble();
        String description = json.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();

        int comfort = ComfortCalculator.calculate(temperature, humidity, wind);

        results.add(new WeatherResult(cityName, temperature, description, comfort, 0));
    }

    results.sort((a,b) -> b.getComfort() - a.getComfort());

    for(int i = 0; results.size() > i; i++){
        results.get(i).setRank(i+1);
    }

    CacheManager.setCacheData(results);
    return results;
}

}
