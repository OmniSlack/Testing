package com.homework.exercise.lecture11.exersie4;

public class Main {
    public static void main(String[] args) {
        Microwave microwave = new Microwave("Sharp", 1100, 180, 25);

        microwave.turnOn();
        microwave.setTemperature(120);
        microwave.putFood(8);
        microwave.startHeating();
        microwave.displayInfo();

        microwave.factoryReset();
        microwave.displayInfo();
    }
}