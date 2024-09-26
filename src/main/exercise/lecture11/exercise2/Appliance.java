package com.homework.exercise.lecture11.exercise2;

public class Appliance {
    String brand;
    int powerConsumption;
    boolean isPowered;

    public Appliance(String brand, int powerConsumption) {
        this.brand = brand;
        this.powerConsumption = powerConsumption;
        this.isPowered = false;
    }

    public void turnOn() {
        isPowered = true;
        System.out.println(brand + " is turned ON.");
    }

    public void turnOff() {
        isPowered = false;
        System.out.println(brand + " is turned OFF.");
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Power Usage: " + powerConsumption + "W");
        System.out.println("Status: " + (isPowered ? "ON" : "OFF"));
    }
}
