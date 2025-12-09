import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Simple inventory management system for tracking items
public class inventoryLogger {
   public static void main(String[] args) {
      // Scanner for user input
      Scanner inputScanner = new Scanner(System.in);
      // ArrayList to store inventory records in memory (dynamic sizing)
      ArrayList<String> inventoryRecords = new ArrayList<>();
      
      System.out.println("=== INVENTORY MANAGEMENT SYSTEM ===");

      // Main program loop - keeps running until user exits
      while(true) {
         while(true) {
            // Display menu options
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("[1] Add Item to Inventory");
            System.out.println("[2] View Inventory Items");
            System.out.println("[3] Exit Program");
            System.out.print("Enter your choice: ");
            int menuChoice = inputScanner.nextInt();
            inputScanner.nextLine(); // Clear the newline
            
            switch (menuChoice) {
               case 1:
                  // Adding new item to inventory
                  System.out.println("\n--- ADD NEW ITEM ---");
                  System.out.print("Enter Item Name: ");
                  String itemName = inputScanner.nextLine();
                  System.out.print("Enter Quantity: ");
                  int quantity = inputScanner.nextInt();
                  inputScanner.nextLine(); // Clear buffer
                  
                  // Determine stock level based on quantity
                  String stockCategory = quantity >= 50 ? "High Stock" : (quantity >= 10 ? "Medium Stock" : (quantity >= 1 ? "Low Stock" : "Out of Stock"));
                  
                  // Show what we just added
                  System.out.println("\nItem: " + itemName);
                  System.out.println("Quantity: " + quantity);
                  System.out.println("Category: " + stockCategory);
                  
                  // Store in ArrayList
                  inventoryRecords.add(itemName + ", " + quantity + ", " + stockCategory);

                  // Save to file
                  try (FileWriter fileWriter = new FileWriter("inventory.txt", true)) {
                      fileWriter.write(itemName + ", " + quantity + ", " + stockCategory + "\n");
                      System.out.println("Item saved to file!");
                  } catch (IOException e) {
                     System.out.println("Error saving to file!");
                  }
                  break;
                  
               case 2:
                  // View all inventory items from file
                  System.out.println("\n--- INVENTORY LIST ---");

                  try {
                     File inventoryFile = new File("inventory.txt");
                     Scanner fileScanner = new Scanner(inventoryFile);
                     boolean hasFileData = false;
                     
                     // Read each line from the file
                     while (fileScanner.hasNextLine()) {
                         String fileLine = fileScanner.nextLine();
                         // Parse and display in formatted way
                         String[] parts = fileLine.split(",", 3);
                         if (parts.length == 3) {
                            System.out.println("Item: " + parts[0].trim());
                            System.out.println("Quantity: " + parts[1].trim());
                            System.out.println("Category: " + parts[2].trim());
                         } else {
                            System.out.println(fileLine);
                         }
                         hasFileData = true;
                     }
                     fileScanner.close();
                     
                     if (!hasFileData) {
                        System.out.println("No inventory data found in file.");
                     }
                  } catch (FileNotFoundException e) {
                     System.out.println("Inventory file not found.");
                  }
                  break;
               case 3:
                  // Exit the program
                  System.out.println("Exiting program. Thank you!");
                  inputScanner.close();
                  System.exit(0);
                  break;
               default:
                  // Handle invalid input
                  System.out.println("Invalid choice! Enter 1, 2, or 3.");
            }
         }
      }
   }
}
