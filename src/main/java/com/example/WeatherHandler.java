package com.example;

import com.sun.net.httpserver.*;
import java.io.*;

public class WeatherHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        if ("OPTIONS".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }


        String cookie = exchange.getRequestHeaders().getFirst("Cookie");

        if (cookie == null || !cookie.startsWith("SESSION=")) {
            exchange.sendResponseHeaders(401, -1);
            return;
        }

        String token = cookie.replace("SESSION=", "");

        if (!SessionManager.isValid(token)) {
            exchange.sendResponseHeaders(403, -1);
            return;
        }

        String response = "{\"status\":\"Authorized Weather Data\"}";
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.close();
    }
}
