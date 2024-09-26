package com.homework.exercise.lecture2.exercise6;

public class ex6
{
    public static void main(String[] args) {

        int minutesInHour = 60;
        int hoursInDay = 24;
        int daysInYear = 365;
        int minutesInAYear = minutesInHour * hoursInDay * daysInYear;


        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("minutes");

        long minutes = scanner.nextLong();
        long years =  minutes / minutesInAYear;
        long days = (minutes / minutesInAYear / hoursInDay) % daysInYear;


        System.out.println("minutes : " + minutes+" years: " + years +" days: " + days  );

    }
}
