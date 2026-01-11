package com.example;

public class ComfortCalculator {

    public static int calculate(double temperature, int humidity, double wind) {

        double temperatureScore = 100 - Math.abs(temperature - 22) * 3;
        double humidityScore = 100 - Math.abs(humidity - 50) * 1.5;
        double windScore = 100 - wind * 5;

        double comfortScore = temperatureScore * 0.5 + humidityScore * 0.3 + windScore * 0.2;

        return (int) Math.max(0, Math.min(100, comfortScore));

    }
}
