package com.homework.exercise.lecture13.exercise1;

public class American extends Person {
    public American(String name, String sex, String religion, String job, String EGN, String countryOfResidence) {
        super(name, sex, religion, "English", job, "American", EGN, countryOfResidence);
    }

    @Override
    public void sayHello() {
        System.out.println("Hello!");
    }

    public void celebrateFourthOfJuly() {
        System.out.println("Celebrating the Fourth of July.");
    }
}

