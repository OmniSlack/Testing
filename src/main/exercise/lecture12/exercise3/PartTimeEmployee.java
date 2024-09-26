package com.homework.exercise.lecture12.exercise3;

public class PartTimeEmployee extends Employee {
    private final double hoursWorked;
    private final double hourlyRate;

    public PartTimeEmployee(int id, String firstName, String lastName, double hoursWorked, double hourlyRate) {
        super(id, firstName, lastName, 0); // Base salary is not needed for part-time employees
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateMonthlySalary() {
        return hoursWorked * hourlyRate;
    }
}

