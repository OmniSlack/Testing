package com.homework.exercise.lecture8.exercise5;

public class SecondLargest {

    public static void main(String[] args) {
        int[] numbers = {22, 44, 17, 39, 54, 33};
        int result = findSecondLargest(numbers);
        System.out.println("The second largest number is: " + result);
    }

    public static int findSecondLargest(int[] numbers) {
        int secondLargest = -1;

        if (numbers.length < 2) {
            return secondLargest;
        }

        int largest = numbers[0];

        for (int num : numbers) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num < largest) {
                secondLargest = num;
            }
        }

        return secondLargest;
    }
}