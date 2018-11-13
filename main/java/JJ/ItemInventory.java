package JJ;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ItemInventory {

    private ArrayList<ItemEntry> entries;
    private static final String separator = "\\|";

    private ItemInventory(ArrayList<ItemEntry> _entries) {
        entries = _entries;
    }

    public static ItemInventory load(String file) {
        File resourceFile = getResourceFile(file);

        ArrayList<ItemEntry> entries = new ArrayList<>();

        try {
            Scanner input = new Scanner(resourceFile);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] data = line.split(ItemInventory.separator);

                String type = data[0];
                double price = Double.valueOf(data[data.length - 2]);

                Item item = null;
                switch(type) {
                    case "music":
                        String dateString = data[3];
                        DateFormat dateFormat = new SimpleDateFormat("d/MM/yyyy");
                        Date releaseDate = dateFormat.parse(dateString);
                        int totalLength = Integer.parseInt(data[6]);
                        item = new MusicCD(
                                data[1],
                                price,
                                data[2],
                                releaseDate,
                                data[4],
                                data[5],
                                totalLength,
                                data[7]
                        );
                        break;
                    case "software":
                        item = new Software(
                                data[1],
                                price,
                                data[2]
                        );
                        break;
                    case "book":
                        int pubYear = Integer.parseInt(data[5]);
                        item = new Book(
                                data[1],
                                price,
                                data[2],
                                data[3],
                                data[4],
                                pubYear
                        );
                        break;
                    default:
                        break;
                }

                int quantity = Integer.parseInt(data[data.length - 1]);
                entries.add(new ItemEntry(
                        item,
                        quantity
                ));
            }
        } catch(Exception e) {
            System.out.println("Error!");
            System.out.println("Error details: " + e.getMessage());
        }

        return new ItemInventory(entries);
    }

    public ArrayList<ItemEntry> getEntries() {
        return this.entries;
    }

    private static File getResourceFile(String filepath) {
        try {
            URL path = Thread.currentThread().getContextClassLoader().getResource(filepath);
            File file = Paths.get(path.toURI()).toFile();
            return file;

        } catch (Exception e) {
            return null;
        }
    }

}
