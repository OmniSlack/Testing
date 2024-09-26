package com.homework.exercise.lecture8.exercise7;

public class LastCharacter {

     public static void main(String[] args) {
        String text = "Java programming!";
        displayLastCharacter(text);
    }

    public static void displayLastCharacter(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("The string is empty; there is no last character.");
        } else {
            char lastChar = text.charAt(text.length() - 1);
            System.out.println("Last character: " + lastChar);
        }
    }
}
