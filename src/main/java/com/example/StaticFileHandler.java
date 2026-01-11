package com.example;

import com.sun.net.httpserver.*;
import java.io.*;

public class StaticFileHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        if ("OPTIONS".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }


        String path = exchange.getRequestURI().getPath();
        if (path.equals("/")) path = "/index.html";

        InputStream is = getClass().getResourceAsStream("/frontend" + path);

        if (is == null) {
            exchange.sendResponseHeaders(404, -1);
            return;
        }

        byte[] data = is.readAllBytes();
        exchange.sendResponseHeaders(200, data.length);
        exchange.getResponseBody().write(data);
        exchange.close();
    }
}
