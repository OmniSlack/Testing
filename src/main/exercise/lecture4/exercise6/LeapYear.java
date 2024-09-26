package com.homework.exercise.lecture4.exercise6;

import java.util.Scanner;

public class LeapYear {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a year: ");
        int year = scanner.nextInt();
        boolean isLeapYear = false;

        if (year % 4 == 0) {
            if (year % 100 != 0 || year % 400 == 0) {
                isLeapYear = true;
            }
        }
        if (isLeapYear) {
            System.out.println("Leap year");
        } else {
            System.out.println("Not a leap year");
        }
        scanner.close();

    }
}
