package com.homework.exercise.lecture11.exercise2;

public class WashingMachine extends Appliance {
    int capacity;

    public WashingMachine(String brand, int powerConsumption, int capacity) {
        super(brand, powerConsumption);
        this.capacity = capacity;
    }

    public void startWashCycle() {
        if (isPowered) {
            System.out.println("Wash cycle started with load capacity: " + capacity + "kg.");
        } else {
            System.out.println("Cannot start the wash cycle. The machine is OFF.");
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Load Capacity: " + capacity + "kg");
    }
}
