package com.homework.exercise.lecture12.exercise3;

public class Main {
    public static void main(String[] args) {
        Employee fullTimeEmployee = new FullTimeEmployee(101, "Mike", "Johnson", 4000);
        Employee partTimeEmployee = new PartTimeEmployee(102, "Emma", "Brown", 90, 25);

        System.out.println(fullTimeEmployee.getName() + " Monthly Salary: " + fullTimeEmployee.calculateMonthlySalary());
        System.out.println(partTimeEmployee.getName() + " Monthly Salary: " + partTimeEmployee.calculateMonthlySalary());
    }
}

