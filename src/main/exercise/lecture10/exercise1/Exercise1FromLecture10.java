/*
Handling User Input Errors - create a program that prompts the user to
        enter an integer. Use a try-catch block to handle situations where the user
        enters something other than an integer (e.g., a letter or symbol). If an error
        occurs, display a friendly error message and prompt the user to try again.
                */

        package com.homework.exercise.lecture10.exercise1;

import java.util.Scanner;

public class Exercise1FromLecture10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Please enter an integer: ");
            try {
                userInput = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: That's not a valid integer.Please try again.");
            }
        }
        System.out.println("You entered the integer: " + userInput);
    }
}
