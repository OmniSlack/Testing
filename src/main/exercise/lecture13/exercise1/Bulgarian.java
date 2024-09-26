package com.homework.exercise.lecture13.exercise1;

public class Bulgarian extends Person {
    public Bulgarian(String name, String sex, String religion, String job, String EGN, String countryOfResidence) {
        super(name, sex, religion, "Bulgarian", job, "Bulgarian", EGN, countryOfResidence);
    }

    @Override
    public void sayHello() {
        System.out.println("Здравей!");
    }

    public void celebrateBabaMarta() {
        System.out.println("Celebrating Baba Marta on March 1st.");
    }
}

