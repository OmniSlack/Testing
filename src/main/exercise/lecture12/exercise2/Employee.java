package com.homework.exercise.lecture12.exercise2;

public class Employee {
    private final int id;
    private final String firstName;
    private final String lastName;
    private double salary;

    public Employee(int id, String firstName, String lastName, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public double getAnnualSalary() {
        return salary * 12;
    }

    public double raiseSalary(double percentage) {
        salary += salary * (percentage / 100);
        return salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + getName() + ", Monthly Salary: " + salary;
    }
}

