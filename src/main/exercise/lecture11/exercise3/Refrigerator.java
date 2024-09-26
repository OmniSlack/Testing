package com.homework.exercise.lecture11.exercise3;

public class Refrigerator extends Appliance {
    int currentTemp;

    public Refrigerator(String brand, int energyConsumption) {
        super(brand, energyConsumption);
        this.currentTemp = 0;
    }

    public void setTemperature(int temp) {
        if (temp >= -3 && temp <= 5) {
            this.currentTemp = temp;
        } else {
            this.currentTemp = 0;
        }
        System.out.println("Temperature adjusted to: " + currentTemp + "°C");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Current Temperature: " + currentTemp + "°C");
    }

    @Override
    public void factoryReset() {
        super.factoryReset();
        this.currentTemp = 0;
    }
}
