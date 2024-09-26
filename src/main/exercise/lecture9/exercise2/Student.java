package com.homework.exercise.lecture9.exercise2;

import java.util.ArrayList;

public class Student {
    String studentName;
    ArrayList<Double> studentGrades;

    public Student(String studentName, ArrayList<Double> studentGrades) {
        this.studentName = studentName;
        this.studentGrades = studentGrades;
    }

    public static void main(String[] args) {
        ArrayList<Double> gradesA = new ArrayList<>();
        gradesA.add(76.3);
        gradesA.add(89.5);
        gradesA.add(91.2);

        ArrayList<Double> gradesB = new ArrayList<>();
        gradesB.add(83.7);
        gradesB.add(95.0);
        gradesB.add(87.4);

        ArrayList<Double> gradesC = new ArrayList<>();
        gradesC.add(65.4);
        gradesC.add(78.9);
        gradesC.add(72.6);

        Student studentA = new Student("Eve", gradesA);
        Student studentB = new Student("Dan", gradesB);
        Student studentC = new Student("Grace", gradesC);

        studentA.printStudentInfo();
        studentB.printStudentInfo();
        studentC.printStudentInfo();
    }

    public double getAverageGrade() {
        double total = 0.0;
        for (double grade : studentGrades) {
            total += grade;
        }
        return total / studentGrades.size();
    }

    public void printStudentInfo() {
        System.out.println("Name: " + studentName);
        System.out.println("Average Grade: " + getAverageGrade());
        System.out.println();
    }
}

