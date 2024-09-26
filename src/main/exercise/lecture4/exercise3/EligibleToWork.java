package com.homework.exercise.lecture4.exercise3;

import java.util.Scanner;

public class EligibleToWork {
    public static void main(String[] args) {
        System.out.println("Please enter your age:");
        {

            String eligibleToWorkInfo = "You are eligible to work";
            String notEligibleToWorkInfo = "You are not eligible to work";

            Scanner scanner = new Scanner(System.in);
            int age = scanner.nextInt();

            if (age >= 16) {
                if (age > 67) {
                    System.out.println(notEligibleToWorkInfo);
                } else {
                    System.out.println(eligibleToWorkInfo);
                }
            } else {

                System.out.println(notEligibleToWorkInfo);
            }

        }
    }
}





