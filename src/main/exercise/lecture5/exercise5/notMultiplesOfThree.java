package com.homework.exercise.lecture5.exercise5;

public class notMultiplesOfThree {
    public static void main(String[] args) {

        int[] numbers = {1, 2, 3, 7, 12, 15, 21, 22, 23, 25, 30};


        for (int number : numbers) {

            if (number % 3 == 0) {
                continue;
            }


            System.out.println(number);
        }
    }

}
