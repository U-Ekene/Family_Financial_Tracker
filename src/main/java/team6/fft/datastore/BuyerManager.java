package team6.fft.datastore;

import java.io.*;
import java.util.*;
//Contributing authors: S Nwachukwu
public class BuyerManager {
    private List<Buyer> buyers;

    public BuyerManager() {
        buyers = new ArrayList<>();
    }
  //Contributing authors: S Nwachukwu
    public void loadBuyersFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Split by comma
                if (parts.length == 2) {
                    String buyerName = parts[0].trim();
                    String buyerID = parts[1].trim();
                    buyers.add(new Buyer(buyerName));
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
  //Contributing authors: S Nwachukwu
    public void displayBuyers() {
        for (Buyer buyer : buyers) {
            System.out.println(buyer);
        }
    }
  //Contributing authors: S Nwachukwu
    public void appendBuyerToFile(String fileName, String buyerName, String buyerID) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) { // 'true' enables append mode
            writer.write(buyerName + ", " + buyerID);
            writer.newLine(); // Move to the next line for next entry
            System.out.println("Buyer successfully added to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }


}

