package com.homework.exercise.lecture5.exercise3;

public class ReverseList {
    public static void main(String[] args) {
        reverseTheList();

    }

    public static void reverseTheList(){
        int[] list = {10, 20, 30, 40, 50};

        for (int i = list.length - 1; i >= 0; i--) {
            System.out.print(list[i] + " ");
        }
    }
}


