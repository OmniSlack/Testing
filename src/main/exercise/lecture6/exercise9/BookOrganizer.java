package com.homework.exercise.lecture6.exercise9;

import java.util.Scanner;
import java.util.Stack;

public class BookOrganizer {
    public static void main(String[] args) {

        Stack<String> bookStack = new Stack<>();
        Scanner scanner = new Scanner(System.in);

        bookStack.push("Book 1: World of Warcraft");
        bookStack.push("Book 2: Lord of the rings");
        bookStack.push("Book 3: Dunes");
        bookStack.push("Book 4: Harry Potter");
        bookStack.push("Book 5: Berserk");

        System.out.println("Current Stack of Books:");
        for (String book : bookStack) {
            System.out.println(book);
        }

        System.out.print("Enter the title of the book you want to find: ");
        String bookToFind = scanner.nextLine();

        Stack<String> tempStack = new Stack<>();
        String foundBook = null;

        while (!bookStack.isEmpty()) {
            String book = bookStack.pop();
            if (book.equalsIgnoreCase(bookToFind)) {
                foundBook = book;
                break;
            } else {
                tempStack.push(book);
            }
        }

        while (!tempStack.isEmpty()) {
            bookStack.push(tempStack.pop());
        }

        if (foundBook != null) {
            System.out.println("Found and retrieved book: " + foundBook);
        } else {
            System.out.println("Book not found in the stack.");
        }

        System.out.println("Updated Stack of Books:");
        for (String book : bookStack) {
            System.out.println(book);
        }
        scanner.close();
    }
}

