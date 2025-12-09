import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class inventoryLogger {
   public static void main(String[] args) {
      Scanner inputScanner = new Scanner(System.in);
      String[] inventoryRecords = new String[100];
      int recordCount = 0;
      System.out.println("=== INVENTORY MANAGEMENT SYSTEM ===");

      while(true) {
         while(true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("[1] Add Item to Inventory");
            System.out.println("[2] View Inventory Items");
            System.out.println("[3] Exit Program");
            System.out.print("Enter your choice: ");
            int menuChoice = inputScanner.nextInt();
            inputScanner.nextLine();
            switch (menuChoice) {
               case 1:
                  System.out.println("\n--- ADD NEW ITEM ---");
                  System.out.print("Enter Item Name: ");
                  String itemName = inputScanner.nextLine();
                  System.out.print("Enter Quantity: ");
                  int quantity = inputScanner.nextInt();
                  inputScanner.nextLine();
                  String stockCategory = quantity >= 50 ? "High Stock" : (quantity >= 10 ? "Medium Stock" : (quantity >= 1 ? "Low Stock" : "Out of Stock"));
                  System.out.println("\nItem: " + itemName);
                  System.out.println("Quantity: " + quantity);
                  System.out.println("Category: " + stockCategory);
                  inventoryRecords[recordCount] = itemName + ", " + quantity + ", " + stockCategory;
                  recordCount++;

                  try (FileWriter fileWriter = new FileWriter("inventory.txt", true)) {
                      fileWriter.write(itemName + ", " + quantity + ", " + stockCategory + "\n");
                      System.out.println("Item saved to file!");
                  } catch (IOException e) {
                     System.out.println("Error saving to file!");
                  }
                  break;
               case 2:
                  System.out.println("\n--- INVENTORY LIST ---");
                  boolean hasMemoryItems = false;
                  for (int memoryIndex = 0; memoryIndex < recordCount; ++memoryIndex) {
                     if (inventoryRecords[memoryIndex] != null) {
                        String[] parts = inventoryRecords[memoryIndex].split(",", 3);
                        if (parts.length == 3) {
                           System.out.println("Item: " + parts[0].trim());
                           System.out.println("Quantity: " + parts[1].trim());
                           System.out.println("Category: " + parts[2].trim());
                        } else {
                           System.out.println(inventoryRecords[memoryIndex]);
                        }
                        hasMemoryItems = true;
                     }
                  }

                  if (!hasMemoryItems) {
                     System.out.println("(No items in memory)");
                  }

                  System.out.println("\n--- FILE CONTENTS ---");

                  try {
                     File inventoryFile = new File("inventory.txt");
                     Scanner fileScanner = new Scanner(inventoryFile);
                     boolean hasFileData = false;
                     while (fileScanner.hasNextLine()) {
                         String fileLine = fileScanner.nextLine();
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
}
