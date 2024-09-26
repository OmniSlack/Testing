package com.homework.exercise.lecture4.exercise1;

import java.util.Scanner;

public class Triangle {
public static void main (String[] args) {
    buildTriangle();

}
    public static void buildTriangle() {
        float angle1, angle2, angle3;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter value for first angle:");
        angle1 = scanner.nextFloat();
        System.out.println("Enter value for second angle:");
        angle2 = scanner.nextFloat();
        System.out.println("Enter value for thrid angle:");
        angle3 = scanner.nextFloat();

        String validMessage = "The triangle can be build! The type of the triangle is: ";

        float sumOfAngles = angle1 + angle2 + angle3;

        if (sumOfAngles != 180) {
            System.out.println("The triangle cannot be build!");

        } else if (angle1 == 60 && angle2 == 60 && angle3 == 60) {
            System.out.println(validMessage + "Equilateral");

        } else if (angle1 == 90 && angle2 == 90 && angle3 == 90) {
            System.out.println(validMessage + "Right-angled");

        } else if (angle1 > 90 && angle2 > 90 && angle3 > 90) {
            System.out.println(validMessage + "Obtuse");


        } else if (angle1 < 90 && angle2 < 90 && angle3 < 90) {
            System.out.println(validMessage + "Acute");

        } else if (angle1 == angle2 || angle1 == angle3 || angle2 == angle3) {
            System.out.println(validMessage + "Isosceles");

        } else {
            System.out.println(validMessage + "Multi-faced");
        }
    }
}