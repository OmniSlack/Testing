package com.homework.exercise.lecture5.exercise1;

public class FindLargestNumber {
public static void main(String[] args) {
    findLargestNumber();

}
    public static void findLargestNumber() {
        int[] arr = {2, 11, 45, 9};

        int largestNumber = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largestNumber) largestNumber = arr[i];
        }

        System.out.println("The largest number is: " + largestNumber);
    }
}
