package com.homework.exercise.lecture11.exersie4;

public class Appliance {
    String brand;
    int powerUsage;
    boolean powered;

    public Appliance(String brand, int powerUsage) {
        this.brand = brand;
        this.powerUsage = powerUsage;
        this.powered = false;
    }

    public void turnOn() {
        powered = true;
        System.out.println(brand + " is turned ON.");
    }

    public void turnOff() {
        powered = false;
        System.out.println(brand + " is turned OFF.");
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Power Usage: " + powerUsage + "W");
        System.out.println("Status: " + (powered ? "ON" : "OFF"));
    }

    public void factoryReset() {
        turnOff();
    }
}

