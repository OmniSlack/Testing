package com.homework.exercise.lecture4.exercise7;

import java.util.Scanner;

public class TemperatureConverter {
    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter temperature: ");
        double selection = scanner.nextDouble();

        System.out.println("Select (1) Fahrenheit or (2) Celsius - enter 1 or 2: ");
        int selected = scanner.nextInt();

       switch (selected) {
            case 1:
                double fahrenheit = (selection * 9/5) + 32;
                System.out.println("Temperature in Fahrenheit: "+ fahrenheit);
                break;
           case 2:
               double celsius = (selection - 32) * 5 / 9;
               System.out.println("Temperature in Celsius: "+ celsius);
               break;
           default:
               System.out.println("Invalid selection. Please enter 1 or 2.");
               break;

        }
    }
}
