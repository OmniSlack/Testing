package com.homework.exercise.lecture12.exercise3;

public abstract class Employee {
    private final int id;
    private final String firstName;
    private final String lastName;
    private double baseSalary;

    public Employee(int id, String firstName, String lastName, double baseSalary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.baseSalary = baseSalary;
    }

    protected double getBaseSalary() {
        return baseSalary;
    }

    public abstract double calculateMonthlySalary();

    public String getName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + getName() + ", Base Salary: " + baseSalary;
    }
}

