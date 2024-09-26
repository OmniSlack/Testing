package com.homework.exercise.lecture5.exercise4;

public class findFirstMultipleOfFive {
    public static void main(String[] args) {
        int[] numbers = {3, 7, 11, 45, 73};
        int firstMultipleOfFive = -1;

        for (int number : numbers) {
            if (number % 5 == 0) {
                firstMultipleOfFive = number;
                break;
            }
        }
         if (firstMultipleOfFive !=-1) {
             System.out.println("The first multiple of 5 in the array is: " + firstMultipleOfFive);
         } else {
             System.out.println("No multiple of 5 found in the array.");

         }
    }
}
