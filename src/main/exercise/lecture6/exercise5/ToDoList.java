package com.homework.exercise.lecture6.exercise5;

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    public static void main(String[] args) {

        ArrayList<String> toDoList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        toDoList.add("Sleeping");
        toDoList.add("Training");
        toDoList.add("Cook");

        System.out.println("To-Do List:");
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println(i + ": " + toDoList.get(i));
        }

        System.out.print("Enter the index of the task you have completed: ");
        int index = scanner.nextInt();

        if (index >= 0 && index < toDoList.size()) {
            toDoList.remove(index);
            System.out.println("Task removed successfully.");
        } else {
            System.out.println("Invalid index. No task was removed.");
        }

        System.out.println("Updated To-Do List:");
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println(i + ": " + toDoList.get(i));
            scanner.close();
        }
    }
}
