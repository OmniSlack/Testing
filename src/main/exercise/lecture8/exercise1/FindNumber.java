package com.homework.exercise.lecture8.exercise1;

public class FindNumber {
    public static double findSmallestNumber(double num1, double num2, double num3) {
        double smallestNumber = num1;

        if (smallestNumber > num2) {
            smallestNumber = num2;
        }

        if (smallestNumber > num3) {
            smallestNumber = num3;
        }

        return smallestNumber;
    }

    public static void main(String[] args) {
        double num1 = 7.0;
        double num2 = 9.5;
        double num3 = 1.1;

        double smallest = findSmallestNumber(num1, num2, num3);
        System.out.println("The smallest number is: " + smallest);
    }
}