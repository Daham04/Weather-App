package com.example;

public class WeatherResult {

    private String city;
    private double temp;
    private String description;
    private int comfort;
    private int rank;

    public WeatherResult(String city, double temp, String description, int comfort, int rank) {
        this.city = city;
        this.temp = temp;
        this.description = description;
        this.comfort = comfort;
        this.rank = rank;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getComfort() {
        return comfort;
    }

    public void setComfort(int comfort) {
        this.comfort = comfort;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
