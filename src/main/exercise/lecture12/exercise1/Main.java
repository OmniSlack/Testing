package com.homework.exercise.lecture12.exercise1;

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(7);
        Shape rectangle = new Rectangle(8, 5);

        System.out.println("Circle Area: " + circle.getArea());
        System.out.println("Circle Perimeter: " + circle.getPerimeter());
        System.out.println("Rectangle Area: " + rectangle.getArea());
        System.out.println("Rectangle Perimeter: " + rectangle.getPerimeter());
    }
}

