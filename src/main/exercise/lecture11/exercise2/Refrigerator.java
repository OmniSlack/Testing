package com.homework.exercise.lecture11.exercise2;

public class Refrigerator extends Appliance {
    int currentTemperature;

    public Refrigerator(String brand, int powerConsumption) {
        super(brand, powerConsumption);
        this.currentTemperature = 0;
    }

    public void setTemperature(int temp) {
        if (temp >= -3 && temp <= 5) {
            this.currentTemperature = temp;
        } else {
            this.currentTemperature = 0;
        }
        System.out.println("Temperature set to: " + this.currentTemperature + "°C");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Current Temperature: " + currentTemperature + "°C");
    }
}
