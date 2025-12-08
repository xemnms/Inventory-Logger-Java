package inventorylogger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class inventoryLogger {
   public inventoryLogger() {
   }

   public static void main(String[] var0) {
      Scanner var1 = new Scanner(System.in);
      boolean var2 = false;
      String[] var3 = new String[100];
      int var4 = 0;
      System.out.println("=== INVENTORY MANAGEMENT SYSTEM ===");

      while(true) {
         while(true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("[1] Add Item to Inventory");
            System.out.println("[2] View Inventory Items");
            System.out.println("[3] Exit Program");
            System.out.print("Enter your choice: ");
            int var15 = var1.nextInt();
            var1.nextLine();
            switch (var15) {
               case 1:
                  System.out.println("\n--- ADD NEW ITEM ---");
                  System.out.print("Enter Item Name: ");
                  String var5 = var1.nextLine();
                  System.out.print("Enter Quantity: ");
                  int var6 = var1.nextInt();
                  var1.nextLine();
                  String var7 = var6 >= 50 ? "High Stock" : (var6 >= 10 ? "Medium Stock" : (var6 >= 1 ? "Low Stock" : "Out of Stock"));
                  System.out.println("\nItem: " + var5);
                  System.out.println("Quantity: " + var6);
                  System.out.println("Category: " + var7);
                  var3[var4] = var5 + ", " + var6 + ", " + var7;
                  ++var4;

                  try {
                     FileWriter var16 = new FileWriter("inventory.txt", true);
                     var16.write(var5 + ", " + var6 + ", " + var7 + "\n");
                     var16.close();
                     System.out.println("Item saved to file!");
                  } catch (IOException var13) {
                     System.out.println("Error saving to file!");
                  }
                  break;
               case 2:
                  System.out.println("\n--- INVENTORY LIST ---");
                  boolean var8 = false;
                  int var9 = 0;

                  for(; var9 < var4; ++var9) {
                     if (var3[var9] != null) {
                        System.out.println(var3[var9]);
                        var8 = true;
                     }
                  }

                  if (!var8) {
                     System.out.println("(No items in memory)");
                  }

                  System.out.println("\n--- FILE CONTENTS ---");

                  try {
                     File var17 = new File("inventory.txt");
                     Scanner var10 = new Scanner(var17);

                     boolean var11;
                     for(var11 = false; var10.hasNextLine(); var11 = true) {
                        String var12 = var10.nextLine();
                        System.out.println(var12);
                     }

                     var10.close();
                     if (!var11) {
                        System.out.println("No inventory data found in file.");
                     }
                  } catch (FileNotFoundException var14) {
                     System.out.println("Inventory file not found.");
                  }
                  break;
               case 3:
                  System.out.println("Exiting program. Thank you!");
                  var1.close();
                  System.exit(0);
                  break;
               default:
                  System.out.println("Invalid choice! Enter 1, 2, or 3.");
            }
         }
      }
   }
}
