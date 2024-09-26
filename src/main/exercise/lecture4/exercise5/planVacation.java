package com.homework.exercise.lecture4.exercise5;

import java.util.Scanner;

public class planVacation {
    public static void main(String[] args) {

        String vacationType;
        double budget;
        double dailyBudget;
        int people;
        int days;
        String bulgariaDestinationMessage = "Available destination: Bulgaria";
        String abroadMessage = "Available destination: abroad";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter vacation type: ");
        vacationType = scanner.nextLine();

        System.out.println("Please enter number of days: ");
        days = scanner.nextInt();

        System.out.println("Please enter number of people: ");
        people = scanner.nextInt();

        System.out.println("Please enter your budget: ");
        budget = scanner.nextDouble();

        dailyBudget = budget / (days * people);

        switch (vacationType) {
            case "Beach":
                if (dailyBudget < 50) {
                    System.out.println(bulgariaDestinationMessage);
                } else {
                    System.out.println(abroadMessage);
                }
                break;
            case "Safari":
                if (dailyBudget < 30) {
                    System.out.println(bulgariaDestinationMessage);
                    {
                    }
                } else {
                    System.out.println(abroadMessage);
                }
                break;
            default:
                System.out.println("There are no result for this type of Vacation");
        }

    }

}

