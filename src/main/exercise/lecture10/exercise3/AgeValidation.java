package com.homework.exercise.lecture10.exercise3;

public class AgeValidation {

    public static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) {
            super(message);
        }
    }


    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 120) {
            throw new InvalidAgeException("Age must be between 0 and 120.");

        } else {
            System.out.println("Age is valid: " + age);

        }
    }


    public static void handleAgeValidation(int age) {
        try {
            validateAge(age);
        } catch (InvalidAgeException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int[] testAges = {25, 150, -5, 45};

        for (int age : testAges) {
            System.out.println("Testing age: " + age);
            handleAgeValidation(age);
            System.out.println();
        }
    }
}

