package com.homework.exercise.lecture9.exercise3;

public class Product {
    String name;
    double price;
    String category;
    int stockQuantity;
    boolean onSale;

    public Product(String name, double price, String category, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;

    }

    public static void main(String[] args) {
        Product laptop = new Product("Laptop", 670.78, "Electornics", 15);
        Product dress = new Product("Dress", 101, "Clothes", 39);
        laptop.putOnSale();
        laptop.printProductDetails();
        dress.printProductDetails();


    }

    public void putOnSale() {
        this.onSale = true;

    }

    public void removeOnSale() {
        this.onSale = false;

    }

    public void printProductDetails() {

        System.out.println("Product Name: " + this.name);
        System.out.println("Product Category: " + this.category);
        System.out.println("Product Price: " + calculatedDiscountedPrice());
        System.out.println();
    }

    public double calculatedDiscountedPrice() {

        if (this.onSale && this.price > 100) {
            return price * 0.90;

        }
        return price;

    }
}
