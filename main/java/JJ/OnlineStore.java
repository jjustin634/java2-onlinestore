package JJ;

import java.util.ArrayList;
import java.util.Scanner;

public class OnlineStore {

    public void start() {
        Scanner input = new Scanner(System.in);
        ItemInventory inventory = ItemInventory.load("OnlineData.txt");

        boolean isRunning = true;
        while(isRunning) {
            int mode;
            System.out.println("Welcome to E-mart");
            System.out.println("\t 1) Show all items");
            System.out.println("\t 2) Show only music CD");
            System.out.println("\t 3) Show only books");
            System.out.println("\t 4) Show only displaySoftware");
            System.out.println("\t 5) Exit program");
            mode = input.nextInt();

            switch (mode) {
                case 1:
                    displayAll(inventory);
                    break;
                case 2:
                    displayMusicCD(inventory);
                    break;
                case 3:
                    displayBooks(inventory);
                    break;
                case 4:
                    displaySoftware(inventory);
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    break;
            }
        }
    }

    private void displayAll(ItemInventory inventory) {
        ArrayList<ItemEntry> entries = inventory.getEntries();
        displayEntries(entries);
    }

    private void displayMusicCD(ItemInventory inventory) {
        ArrayList<ItemEntry> entries = filter(inventory.getEntries(), "MusicCD");
        displayEntries(entries);
    }

    private void displayBooks(ItemInventory inventory) {
        ArrayList<ItemEntry> entries = filter(inventory.getEntries(), "Book");
        displayEntries(entries);
    }

    private void displaySoftware(ItemInventory inventory) {
        ArrayList<ItemEntry> entries = filter(inventory.getEntries(), "Software");
        displayEntries(entries);
    }

    private void displayEntries(ArrayList<ItemEntry> entries) {
        printHeader();
        for (ItemEntry itemEntry : entries) {
            Item item = itemEntry.getItem();

            System.out.printf(
                    "%-30s %10s %10.2f %10d\n",
                    item.getTitle(),
                    OnlineStore.getType(item),
                    item.getPrice(),
                    itemEntry.getQuantity()
            );
        }
        printFooter();
    }

    private ArrayList<ItemEntry> filter(ArrayList<ItemEntry> entries, String type) {
        ArrayList<ItemEntry> filtered = new ArrayList<>();

        for (ItemEntry itemEntry : entries) {
            Item item = itemEntry.getItem();

            if (getType(item).equals(type)) {
                filtered.add(itemEntry);
            }
        }

        return filtered;
    }

    private static String getType(Item item) {
        if (item instanceof Book) {
            return "Book";
        } else if (item instanceof Software) {
            return "Software";
        } else if (item instanceof MusicCD) {
            return "MusicCD";
        } else {
            return "Unknown";
        }
    }

    private void printHeader() {
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-30s %10s %10s %10s\n", "Title", "Type", "Price", "Quantity");
        System.out.println("-----------------------------------------------------------------");
    }

    private void printFooter() {
        System.out.println("-----------------------------------------------------------------");
    }
}