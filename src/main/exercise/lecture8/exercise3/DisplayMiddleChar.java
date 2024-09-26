package com.homework.exercise.lecture8.exercise3;

public class DisplayMiddleChar {

    public static void main(String[] args) {
        String value = "teleportation";
        displayMiddleChar(value);
    }
    public static void displayMiddleChar(String value) {
        int position;
        int length;

                if (value.length() % 2 == 0) {
            position = value.length() / 2 - 1;
            length = 2;
        } else {
            position = value.length() / 2;
            length = 1;
        }
        System.out.println(value.substring(position, position + length));
    }
}