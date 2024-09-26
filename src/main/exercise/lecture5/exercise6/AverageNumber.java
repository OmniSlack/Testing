package com.homework.exercise.lecture5.exercise6;

import java.util.Scanner;

public class AverageNumber {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int count = 0;
        int number;
        System.out.println("Enter numbers to calculate the average. Enter 0 or a negative value to finish.");
        while (true) {
            number = scanner.nextInt();
            if (number <= 0) {
                break;
            }
            sum += number;
            count++;
        }
        if (count > 0) {
            double average = (double) sum / count;
            System.out.println("The average of the entered numbers is: " + average);
        } else {
            System.out.println("No positive numbers were entered.");
        }
        scanner.close();
    }
}
