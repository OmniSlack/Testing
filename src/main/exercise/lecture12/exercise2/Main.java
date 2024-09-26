package com.homework.exercise.lecture12.exercise2;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee(101, "John", "Doe", 3200);

        System.out.println("Employee Info: " + employee);
        System.out.println("Annual Salary: " + employee.getAnnualSalary());

        employee.raiseSalary(12);
        System.out.println("New Salary after 12% raise: " + employee.getAnnualSalary());
    }
}

