package com.homework.exercise.lecture7.exercise1;

import java.util.HashMap;

public class Inventory {
    public static void main(String[] args) {

        HashMap<String, Integer> inventory = new HashMap<>();
        inventory.put("Apples", 50);
        inventory.put("Bananas", 30);
        inventory.put("Oranges", 20);
        inventory.put("Milk", 10);
        inventory.put("Bread", 15);

        System.out.println("Initial Inventory: " + inventory);

        System.out.println("\nChecking quantity of Bananas: " + inventory.get("Bananas") + " units");


        inventory.put("Oranges", inventory.get("Oranges") + 10);
        System.out.println("\nRestocked Oranges. New quantity: " + inventory.get("Oranges") + " units");

        inventory.put("Milk", 0);
        System.out.println("\nMilk is now out of stock. Updated Inventory: " + inventory);

        inventory.remove("Bread");
        System.out.println("\nRemoved Bread from inventory. Updated Inventory: " + inventory);

        System.out.println("\nFinal Inventory: " + inventory);
    }
}
