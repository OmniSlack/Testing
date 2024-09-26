package com.homework.exercise.lecture8.exercise9;

import java.util.Scanner;

public class PalindromeChecker {

    public static void main(String[] args) {
        boolean result = isPalindrome();
        System.out.println("Is the number a palindrome? " + result);
    }

    public static boolean isPalindrome() {
        int remainder;
        int sum = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a non-negative integer: ");

        int num = scanner.nextInt();

        if (num >= 0) {
            int tempNum = num;

            while (tempNum > 0) {
                remainder = tempNum % 10;
                sum = (sum * 10) + remainder;
                tempNum = tempNum / 10;
            }
            return num == sum;
        } else {
            System.out.println("The number must be non-negative.");
            return false;
        }
    }
}