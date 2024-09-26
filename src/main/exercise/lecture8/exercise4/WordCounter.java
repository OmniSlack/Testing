package com.homework.exercise.lecture8.exercise4;

public class WordCounter {

    public static void main(String[] args) {
        String sentence = "This is a sample sentence to count words.";
        countWords(sentence);
    }

    public static void countWords(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            System.out.println("The sentence is empty.");
            return;
        }

        int wordCount = 0;
        boolean isWord = false;
        int endOfLine = sentence.length() - 1;

        for (int i = 0; i < sentence.length(); i++) {

            if (Character.isLetter(sentence.charAt(i)) && i != endOfLine) {
                isWord = true;
            }

            else if (!Character.isLetter(sentence.charAt(i)) && isWord) {
                wordCount++;
                isWord = false;
            }

            else if (Character.isLetter(sentence.charAt(i)) && i == endOfLine) {
                wordCount++;
            }
        }

        System.out.println("The number of words in the sentence is: " + wordCount);
    }
}