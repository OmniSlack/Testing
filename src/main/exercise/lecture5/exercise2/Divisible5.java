package com.homework.exercise.lecture5.exercise2;

public class Divisible5 {

    public static void main(String[] args) {
        printDivisibleNumbers();

    }
    public static void printDivisibleNumbers() {
        int [] list = {12, 15, 32, 42, 55, 75, 122, 132, 150, 180, 200};

        for (int number : list) {
            if ((number % 5) == 0) {
                System.out.println(number);
                if(number > 150) {
                    break;
                }
            }
        }
    }

}
