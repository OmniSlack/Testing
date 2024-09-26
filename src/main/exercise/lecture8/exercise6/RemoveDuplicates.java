package com.homework.exercise.lecture8.exercise6;

import java.util.HashSet;

public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] numbers = {4, 5, 6, 7, 4, 6, 8};
        int[] result = removeDuplicates(numbers);
        System.out.print("Array with unique elements: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    public static int[] removeDuplicates(int[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }

        HashSet<Integer> uniqueNumbers = new HashSet<>();

        for (int num : numbers) {
            uniqueNumbers.add(num);
        }

        int[] uniqueArray = new int[uniqueNumbers.size()];
        int index = 0;
        for (int num : uniqueNumbers) {
            uniqueArray[index++] = num;
        }

        return uniqueArray;
    }
}