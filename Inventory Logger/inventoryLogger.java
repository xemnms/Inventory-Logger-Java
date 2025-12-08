import java.io.*;
import java.util.Scanner;

public class InventoryLogger {
    
    public static void main(String[] args) {
        // Scanner for user input
        Scanner inputScanner = new Scanner(System.in);
        
        // Variable for menu choice
        int menuSelection = 0;
        
        // Array to temporarily store inventory (optional feature)
        String[] temporaryStorage = new String[100];
        int storageIndex = 0;
        
        System.out.println("=== INVENTORY MANAGEMENT SYSTEM ===");
        
        // Main menu loop
        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("[1] Add Item to Inventory");
            System.out.println("[2] View Inventory Items");
            System.out.println("[3] Exit Program");
            System.out.print("Enter your choice: ");
            
            menuSelection = inputScanner.nextInt();
            inputScanner.nextLine(); // Clear buffer
            
            // Switch statement for menu options
            switch (menuSelection) {
                case 1:
                    String itemName;
                    int itemQuantity;
                    String stockCategory;
                    
                    System.out.println("\n--- ADD NEW ITEM ---");
                    
                    System.out.print("Enter Item Name: ");
                    itemName = inputScanner.nextLine();
                    
                    System.out.print("Enter Quantity: ");
                    itemQuantity = inputScanner.nextInt();
                    inputScanner.nextLine();
                    
                    // Ternary operator for stock categorization
                    stockCategory = (itemQuantity >= 50) ? "High Stock" :
                                   (itemQuantity >= 10) ? "Medium Stock" :
                                   (itemQuantity >= 1) ? "Low Stock" : "Out of Stock";
                    
                    System.out.println("\nItem: " + itemName);
                    System.out.println("Quantity: " + itemQuantity);
                    System.out.println("Category: " + stockCategory);
                    
                    // Store in array for optional memory storage
                    temporaryStorage[storageIndex] = itemName + ", " + itemQuantity + ", " + stockCategory;
                    storageIndex++;
                    
                    // Save to file using FileWriter only
                    try {
                        FileWriter fileWriter = new FileWriter("inventory.txt", true);
                        fileWriter.write(itemName + ", " + itemQuantity + ", " + stockCategory + "\n");
                        fileWriter.close();
                        System.out.println("Item saved to file!");
                    } catch (IOException fileError) {
                        System.out.println("Error saving to file!");
                    }
                    break;
                    
                case 2:
                    System.out.println("\n--- INVENTORY LIST ---");
                    
                    // Optional: Display from array first
                    boolean arrayHasData = false;
                    for (int i = 0; i < storageIndex; i++) {
                        if (temporaryStorage[i] != null) {
                            System.out.println(temporaryStorage[i]);
                            arrayHasData = true;
                        }
                    }
                    
                    if (!arrayHasData) {
                        System.out.println("(No items in memory)");
                    }
                    
                    System.out.println("\n--- FILE CONTENTS ---");
                    
                    // Read from file
                    try {
                        File inventoryFile = new File("inventory.txt");
                        Scanner fileScanner = new Scanner(inventoryFile);
                        
                        boolean fileHasData = false;
                        
                        while (fileScanner.hasNextLine()) {
                            String fileLine = fileScanner.nextLine();
                            System.out.println(fileLine);
                            fileHasData = true;
                        }
                        
                        fileScanner.close();
                        
                        if (!fileHasData) {
                            System.out.println("No inventory data found in file.");
                        }
                        
                    } catch (FileNotFoundException fileNotFound) {
                        System.out.println("Inventory file not found.");
                    }
                    break;
                    
                case 3:
                    System.out.println("Exiting program. Thank you!");
                    inputScanner.close();
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Invalid choice! Enter 1, 2, or 3.");
            }
        }
    }
}