package com.homework.exercise.lecture13.exercise1;

import java.util.List;

public class Italian extends Person {
    public Italian(String name, String sex, String religion, String job, String EGN, String countryOfResidence) {
        super(name, sex, religion, "Italian", job, "Italian", EGN, countryOfResidence);
    }

    @Override
    public void sayHello() {
        System.out.println("Ciao!");
    }

    public void makePasta(List<String> ingredients) {
        System.out.println("Making pasta with: " + ingredients);
    }
}

