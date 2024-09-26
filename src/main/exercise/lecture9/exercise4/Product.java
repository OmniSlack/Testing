package com.homework.exercise.lecture9.exercise4;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private static final List<Product> productList = new ArrayList<>();
    private static String currentSaleCategory = null;

    private final String productName;
    private final double productPrice;
    private String productCategory;
    private boolean isOnSale;

    public Product(String productName, double productPrice, String productCategory) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        productList.add(this);
        updateSaleCondition();
    }

    public static void setSaleCategory(String category) {
        currentSaleCategory = category;
        updateSaleForAllProducts();
    }

    private static void updateSaleForAllProducts() {
        for (Product product : productList) {
            product.updateSaleCondition();
        }
    }

    public static void main(String[] args) {
        Product item1 = new Product("Tablet", 300, "Gadgets");
        Product item2 = new Product("Sneakers", 120, "Footwear");
        Product item3 = new Product("Coffee Maker", 85, "Kitchen");

        Product.setSaleCategory("Gadgets");

        item1.displayProductDetails();
        item2.displayProductDetails();
        item3.displayProductDetails();

        item1.setProductCategory("Footwear");

        item1.displayProductDetails();
        item2.displayProductDetails();
        item3.displayProductDetails();

        Product.setSaleCategory("Footwear");

        item1.displayProductDetails();
        item2.displayProductDetails();
        item3.displayProductDetails();
    }

    public double getDiscountedPrice() {
        if (isOnSale && productPrice > 100) {
            return productPrice * 0.90;
        }
        return productPrice;
    }

    public void displayProductDetails() {
        System.out.println("Product: " + productName);
        System.out.println("Category: " + productCategory);
        System.out.println("Price: $" + getDiscountedPrice());
    }

    public void setProductCategory(String newCategory) {
        if (!newCategory.equals(this.productCategory)) {
            this.productCategory = newCategory;
            updateSaleCondition();
        }
    }

    private void updateSaleCondition() {
        this.isOnSale = productCategory.equals(currentSaleCategory);
    }
}


