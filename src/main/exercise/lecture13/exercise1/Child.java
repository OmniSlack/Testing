package com.homework.exercise.lecture13.exercise1;

public class Child extends Person {
    public Child(String name, String sex, String religion, String languageSpoken, String nationality, String EGN, String countryOfResidence) {
        super(name, sex, religion, languageSpoken, null, nationality, EGN, countryOfResidence);
    }

    @Override
    public void sayHello() {
        System.out.println("Hi!");
    }

    @Override
    public boolean isAdult() {
        System.out.println("A child is not an adult.");
        return false;
    }

    @Override
    public boolean canTakeLoan() {
        System.out.println("A child cannot take a loan.");
        return false;
    }

    public void play(String toy) {
        System.out.println("Playing with " + toy);
    }
}

