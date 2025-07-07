package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> inventory = new HashMap<>();

        int option = 0;
        do{
            printMenu();
            option = scanner.nextInt();

            if(option == 1){
                viewInventory(inventory);
            }
            else if(option == 2){

                System.out.print("Enter product name: ");
                String productName = scanner.next();

                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();

                System.out.println(addProduct(inventory, productName, quantity));

            }
            else if(option == 3){
                System.out.print("Enter product name to check: ");
                String productName = scanner.next();

                System.out.println(checkProduct(inventory, productName));
            }
            else if(option == 4){
                System.out.print("Enter product name to update: ");
                String productName = scanner.next();

                System.out.println(updateProduct(inventory, productName));
            }
            else if(option == 5){

                System.out.print("Enter product name to remove: ");
                String productName = scanner.next();

                System.out.println(removeProduct(inventory, productName));
            }
            else if(option != 6){
                System.out.println("Invalid option.");
            }

            System.out.println();
        }while(option != 6);
        System.out.println("Exiting system...");
    }

    private static String addProduct(HashMap<String, Integer> inventory, String productName, int quantity) {
        if(quantity <= 0){
            return "Invalid quantity";
        }

        //Check if product already existing
        if(inventory.containsKey(productName)){
            inventory.compute(productName, (k, currentQuantity) -> currentQuantity + quantity);
        }else{
            inventory.put(productName, quantity);
        }
        return "Product added!";
    }

    private static String checkProduct(HashMap<String, Integer> inventory, String productName) {
        String result;
        if(inventory.containsKey(productName)){
            result = productName + " is in stock: " + inventory.get(productName);
            return result;
        }
        else{
            result = productName + " is not in the inventory.";
            return result;
        }
    }

    private static String updateProduct(HashMap<String, Integer> inventory, String productName) {
        Scanner scanner = new Scanner(System.in);
        String result;

        if(inventory.containsKey(productName)){
            System.out.print("Enter new stock quantity: ");
            int newStockQuantity = scanner.nextInt();

            //validate if newStockQuantity is positive
            if(newStockQuantity <= 0){
                result = "Invalid quantity.";
                return result;
            }
            else{
                inventory.put(productName, newStockQuantity);
                result = "Stock updated.";
                return result;
            }
        }
        else{
            result = productName + "is not in the inventory";
            return result;
        }
    }

    private static String removeProduct(HashMap<String, Integer> inventory, String productName) {
        Scanner scanner = new Scanner(System.in);
        String result;

        if(inventory.containsKey(productName)){
            inventory.remove(productName);
            result = "Product removed.";
            return result;
        }
        else{
            result = productName + "is not in the inventory";
            return result;
        }
    }

    private static void viewInventory(HashMap<String, Integer> products) {
        System.out.println("Current Inventory:");
        for(Map.Entry<String, Integer> entry : products.entrySet()){
            System.out.println(entry.getKey() + " - " + entry.getValue() + " pcs");
        }
    }

    private static void printMenu() {
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