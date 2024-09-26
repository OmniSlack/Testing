package com.homework.exercise.lecture6.exercise1;

public class ArraySum {
    public static void main(String[] args) {


        double[] numbers = {0, 0.5, 1, 1.5, 2, 2.5, 3};
        double sum = 0;

        for (double num : numbers) {
            sum += num;
        }

        System.out.println("The sum of the array is: " + sum);
    }
    }
