package com.homework.exercise.lecture11.exersie4;

public class Microwave extends Appliance {
    int maxTemp;
    int currentTemp;
    int capacity;
    int load;

    public Microwave(String brand, int powerUsage, int maxTemp, int capacity) {
        super(brand, powerUsage);
        this.maxTemp = maxTemp;
        this.currentTemp = 0;
        this.capacity = capacity;
        this.load = 0;
    }

    public void setTemperature(int temp) {
        if (temp >= 10 && temp <= maxTemp) {
            this.currentTemp = temp;
            System.out.println("Temperature set to: " + currentTemp + "°C");
        } else {
            System.out.println("Temperature must be between 10°C and " + maxTemp + "°C");
        }
    }

    public void putFood(int foodVolume) {
        if (load + foodVolume <= capacity) {
            load += foodVolume;
            System.out.println("Added " + foodVolume + " liters of food. Current load: " + load + " liters.");
        } else {
            System.out.println("Cannot add food. Exceeds capacity of " + capacity + " liters.");
        }
    }

    public void startHeating() {
        if (currentTemp > 0) {
            System.out.println("Heating food at " + currentTemp + "°C.");
        } else {
            System.out.println("Cannot start heating. Temperature is not set.");
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Max Temperature: " + maxTemp + "°C");
        System.out.println("Current Temperature: " + currentTemp + "°C");
        System.out.println("Capacity: " + capacity + " liters");
        System.out.println("Current Load: " + load + " liters");
    }

    @Override
    public void factoryReset() {
        super.factoryReset();
        this.currentTemp = 0;
        this.load = 0;
    }
}
