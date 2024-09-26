package com.homework.exercise.lecture6.exercise8;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ShopLineManager {
    public static void main(String[] args) {

        Queue<String> customerQueue = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        customerQueue.add("Customer 1: Assia");
        customerQueue.add("Customer 2: Emi");
        customerQueue.add("Customer 3: Stasi");
        customerQueue.add("Customer 4: Iva");
        customerQueue.add("Customer 5: Ori");

        System.out.println("Current Queue of Customers:");
        for (String customer : customerQueue) {
            System.out.println(customer);
        }
        System.out.println("Serving customers...");
        while (true) {

            String customer = customerQueue.poll();
            System.out.println("Serving: " + customer);

            if (customerQueue.isEmpty()) {
                System.out.println("All customers have been served.");
                break;
            }

            System.out.println("Current Queue of Customers:");
            for (String customerInLine : customerQueue) {
                System.out.println(customerInLine);
                scanner.close();
            }
        }
    }
}
