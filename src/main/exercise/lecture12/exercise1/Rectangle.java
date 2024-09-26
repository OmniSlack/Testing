package com.homework.exercise.lecture12.exercise1;

public class Rectangle implements Shape {
    private final double height;
    private final double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public Double getArea() {
        return height * width;
    }

    @Override
    public Double getPerimeter() {
        return 2 * (height + width);
    }
}

