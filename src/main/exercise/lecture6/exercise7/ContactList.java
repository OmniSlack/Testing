package com.homework.exercise.lecture6.exercise7;

import java.util.LinkedList;
import java.util.Scanner;

public class ContactList {
    public static void main(String[] args) {

        LinkedList<String> contactList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        contactList.add("Koicho");
        contactList.add("Pencho");
        contactList.add("Strahil");
        contactList.add("Jeff");

        System.out.println("Contact List:");
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println((i + 1) + ": " + contactList.get(i));
        }

        System.out.print("Enter the position of the contact to remove (1-" + contactList.size() + "): ");
        int position = scanner.nextInt() - 1;

        if (position >= 0 && position < contactList.size()) {
            String removedContact = contactList.remove(position);
            System.out.println("Removed contact: " + removedContact);
        } else {
            System.out.println("Invalid position. No contact was removed.");
        }

        System.out.println("Updated Contact List:");
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println((i + 1) + ": " + contactList.get(i));
            scanner.close();
        }
    }
}
