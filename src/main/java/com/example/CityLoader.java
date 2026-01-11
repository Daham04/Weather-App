package com.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CityLoader {

    public static List<String> loadCityCodes() {

        try {
            InputStream is = CityLoader.class
                    .getResourceAsStream("/cities.json");

            if (is == null) {
                throw new RuntimeException("cities.json NOT found in classpath");
            }

            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8);

            JsonObject obj = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray list = obj.getAsJsonArray("List");

            List<String> cityId = new ArrayList<>();

            for (JsonElement e : list) {
                cityId.add(e.getAsJsonObject()
                        .get("CityCode")
                        .getAsString());
            }

            return cityId;

        } catch (Exception e) {
            throw new RuntimeException("Failed to load cities.json", e);
        }
    }
}
