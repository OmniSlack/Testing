package com.homework.exercise.lecture4.exercise4;

import java.util.Scanner;

public class RevenueCalculator {
    public static void main(String[] args) {

        double revenue;
        double unitPrice;
        int quantity;
        double discountRate;
        double discount;
        String revenueMessage = "The revenue from sale: ";
        String discountMessage = "Discount: ";
        char dollar = '$';
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter unit price: ");
        unitPrice = scanner.nextDouble();

        System.out.println("Please enter quantity: ");
        quantity = scanner.nextInt();

        revenue = unitPrice * quantity;

        if (quantity >= 100 && quantity <= 120) {
            discountRate = 0.15;
        } else if (quantity > 120) {
        discountRate = 0.20;
    } else {

        discountRate = 0;

    }

discount = revenue * discountRate;
        revenue = revenue - discount;

        System.out.println(revenueMessage + revenue + dollar);
        System.out.println(discountMessage + discount + dollar);
    }
}
