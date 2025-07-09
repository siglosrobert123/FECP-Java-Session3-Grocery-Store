package org.example;

import java.util.HashMap;
import java.util.Map;

public class PrintHelper {
    public static void viewInventory(HashMap<String, Integer> products) {
        System.out.println("Current Inventory:");
        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + " pcs");
        }
    }

    public static void printMenu() {
        System.out.println("--- Product Menu ---");
        System.out.println("1. View Inventory");
        System.out.println("2. Add Product");
        System.out.println("3. Check Product");
        System.out.println("4. Update Stock");
        System.out.println("5. Remove Product");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }
}
