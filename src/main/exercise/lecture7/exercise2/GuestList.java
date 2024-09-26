package com.homework.exercise.lecture7.exercise2;

import java.util.HashSet;

public class GuestList {
    public static void main(String[] args) {
    HashSet<String> guestList = new HashSet<>();
        guestList.add("Alice");
        guestList.add("Bob");
        guestList.add("Charlie");
        guestList.add("Diana");
        guestList.add("Eve");


        System.out.println("Initial Guest List: " + guestList);
        boolean isAdded = guestList.add("Alice");

        System.out.println("\nTrying to add Alice again: " + (isAdded ? "Added" : "Already on the list"));
        guestList.remove("Charlie");

        System.out.println("\nCharlie can't attend. Updated Guest List: " + guestList);
        boolean isOnList = guestList.contains("Diana");

        System.out.println("\nIs Diana on the guest list? " + (isOnList ? "Yes" : "No"));
        System.out.println("\nFinal Guest List: " + guestList);

    }

}
