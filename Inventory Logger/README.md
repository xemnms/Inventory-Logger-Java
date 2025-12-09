# Inventory Logger

A simple command-line inventory management system built in Java. This program allows users to add items to their inventory, automatically categorize them based on stock levels, and view all saved items from a persistent file.

## Features

- **Add Items**: Add new items with name and quantity to your inventory
- **Automatic Stock Categorization**: Items are automatically categorized by quantity:
  - High Stock: 50+ units
  - Medium Stock: 10-49 units
  - Low Stock: 1-9 units
  - Out of Stock: 0 units
- **View Inventory**: Display all items saved in the inventory file with formatted output
- **Persistent Storage**: All items are saved to `inventory.txt` and persist between program runs
- **Input Validation**: Empty item names are rejected to prevent invalid entries

## How It Works

The program presents a menu-driven interface with three main options:
1. Add new items to the inventory with automatic stock categorization
2. View all items currently stored in the inventory file
3. Exit the program

All data is stored directly in a text file, making it easy to backup and transfer your inventory data.

## Technical Details

- **Language**: Java
- **Storage**: File-based (inventory.txt)
- **Dependencies**: None - uses only Java standard library
- **Data Format**: Comma-separated values (ItemName, Quantity, StockCategory)
