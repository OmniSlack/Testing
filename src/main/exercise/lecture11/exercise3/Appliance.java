package com.homework.exercise.lecture11.exercise3;

public class Appliance {
    String brand;
    int energyConsumption;
    boolean powered;

    public Appliance(String brand, int energyConsumption) {
        this.brand = brand;
        this.energyConsumption = energyConsumption;
        this.powered = false;
    }

    public void turnOn() {
        powered = true;
        System.out.println(brand + " is powered ON.");
    }

    public void turnOff() {
        powered = false;
        System.out.println(brand + " is powered OFF.");
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Energy Use: " + energyConsumption + "W");
        System.out.println("Status: " + (powered ? "ON" : "OFF"));
    }

    public void factoryReset() {
        turnOff();
    }
}

