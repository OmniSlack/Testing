package com.homework.exercise.lecture11.exercise3;

public class Main {
    public static void main(String[] args) {
        Appliance appliance1 = new WashingMachine("Electrolux", 1600, 8);
        Appliance appliance2 = new Refrigerator("Frigidaire", 250);

        appliance1.turnOn();
        ((WashingMachine) appliance1).startWashCycle();
        appliance1.displayInfo();

        appliance2.turnOn();
        ((Refrigerator) appliance2).setTemperature(3);
        appliance2.displayInfo();

        appliance1.factoryReset();
        appliance1.displayInfo();

        appliance2.factoryReset();
        appliance2.displayInfo();
    }
}