package com.homework.exercise.lecture8.exercise2;

public class AverageNumberCalculator {

    public static double computeAverageNumber(double num1, double num2, double num3) {
        return (num1 + num2 + num3) / 3;
    }

    public static void main(String[] args) {
        double num1 = 5.8;
        double num2 = 7.2;
        double num3 = 3.9;

        double average = computeAverageNumber(num1, num2, num3);
        System.out.println("The average of the three numbers is: " + average);
    }
}
