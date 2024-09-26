package com.homework.exercise.lecture11.exercise3;

public class WashingMachine extends Appliance {
    int capacity;

    public WashingMachine(String brand, int energyConsumption, int capacity) {
        super(brand, energyConsumption);
        this.capacity = capacity;
    }

    public void startWashCycle() {
        if (powered) {
            System.out.println("Wash cycle initiated with capacity: " + capacity + "kg.");
        } else {
            System.out.println("Cannot start wash cycle. Washing machine is OFF.");
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Washing Capacity: " + capacity + "kg");
    }

    @Override
    public void factoryReset() {
        super.factoryReset();
        this.capacity = 0;
    }
}
