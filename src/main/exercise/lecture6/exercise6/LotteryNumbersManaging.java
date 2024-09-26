package com.homework.exercise.lecture6.exercise6;

import java.util.ArrayList;
import java.util.Scanner;

public class LotteryNumbersManaging {
    public static void main(String[] args) {

        ArrayList<Integer> lotteryNumbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        lotteryNumbers.add(3);
        lotteryNumbers.add(25);
        lotteryNumbers.add(31);
        lotteryNumbers.add(86);
        lotteryNumbers.add(100);

        System.out.print("Enter a lottery number to see if it's a winning number: ");
        int userNumber = scanner.nextInt();

        if (lotteryNumbers.contains(userNumber)) {
            System.out.println("Congratulations! " + userNumber + " is a winning number!");
        } else {
            System.out.println("Sorry, " + userNumber + " is not a winning number.");
        }

        System.out.println("Winning Lottery Numbers:");
        for (int number : lotteryNumbers) {
            System.out.println(number);
        }

        scanner.close();
    }
}
