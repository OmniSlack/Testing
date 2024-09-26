package com.homework.exercise.lecture13.exercise1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Child child = new Child("Tom", "male", "Catholic", "English", "American", "0304051234", "USA");
        Bulgarian bulgarian = new Bulgarian("Georgi", "male", "Orthodox", "Engineer", "7010103456", "Bulgaria");
        American american = new American("Emily", "female", "Catholic", "Teacher", "8504042345", "USA");
        Italian italian = new Italian("Luca", "male", "Catholic", "Chef", "7302025678", "Italy");

        child.sayHello();
        child.celebrateEaster();
        System.out.println("Is adult: " + child.isAdult());
        System.out.println("Can take loan: " + child.canTakeLoan());
        child.play("ball");

        bulgarian.sayHello();
        bulgarian.celebrateEaster();
        System.out.println("Is adult: " + bulgarian.isAdult());
        System.out.println("Can take loan: " + bulgarian.canTakeLoan());
        bulgarian.celebrateBabaMarta();

        american.sayHello();
        american.celebrateEaster();
        System.out.println("Is adult: " + american.isAdult());
        System.out.println("Can take loan: " + american.canTakeLoan());
        american.celebrateFourthOfJuly();

        italian.sayHello();
        italian.celebrateEaster();
        System.out.println("Is adult: " + italian.isAdult());
        System.out.println("Can take loan: " + italian.canTakeLoan());
        italian.makePasta(List.of("tomato", "garlic", "olive oil"));
    }
}

