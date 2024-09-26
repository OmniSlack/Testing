package com.homework.exercise.lecture8.exercise8;

public class IsEven {

    public static void main(String[] args) {
        int number = 85;
        boolean result = isEven(number);
        System.out.println("Is " + number + " even? " + result);
    }

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }
}