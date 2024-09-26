package com.homework.exercise.lecture11.exercise2;

public class Main {
    public static void main(String[] args) {
        Appliance appliance1 = new WashingMachine("Bosch", 1800, 9);
        Appliance appliance2 = new Refrigerator("Whirlpool", 220);

        appliance1.turnOn();
        appliance1.displayInfo();
        ((WashingMachine) appliance1).startWashCycle();

        appliance2.turnOn();
        appliance2.displayInfo();
        ((Refrigerator) appliance2).setTemperature(2);
        appliance2.displayInfo();
    }
}
