package com.homework.exercise.lecture5.exercise7;

import java.util.Scanner;
import java.util.Random;

public class GuessTheWord {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] wordsToGuess = {"apple", "banana", "orange", "grape", "mango"};
        int randomIndex = random.nextInt(wordsToGuess.length);
        String wordToGuess = wordsToGuess[randomIndex];
        String userGuess;

        do {
            System.out.print("Guess the word (hint: it's a fruit): ");
            userGuess = scanner.nextLine().toLowerCase();

            if (!userGuess.equals(wordToGuess)) {
                System.out.println("Wrong guess! Try again.");
            } else {
                System.out.println("Congratulations! You've guessed the word.");
            }
        } while (!userGuess.equals(wordToGuess));


        scanner.close();
    }
}

