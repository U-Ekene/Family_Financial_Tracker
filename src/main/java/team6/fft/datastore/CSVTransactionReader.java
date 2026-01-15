package team6.fft.datastore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import team6.fft.model.Transaction;

//Contributing authors: B Oladokun, E Unaigwe
public class CSVTransactionReader {
	
	//Contributing authors: B Oladokun, E Unaigwe
	
	private static final AutoAssignCategories assign = new AutoAssignCategories();
	
    public static ArrayList<Transaction> readTransactions(String filePath) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", -1); // Include empty fields
                if (fields.length < 5) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                // Parse fields
                LocalDate date = LocalDate.parse(fields[0], formatter);
                String description = fields[1];
                double debit = fields[2].isEmpty() ? 0 : Double.parseDouble(fields[2]);
                double credit = fields[3].isEmpty() ? 0 : Double.parseDouble(fields[3]);
                double value = credit > 0 ? credit : -debit;

                // Create Buyer object (assuming it's a simple class with a name)
                // Replace with actual logic if available
                
                //Determine the category
                String category = assign.getCategory(description);

                // Create Transaction object
                Transaction transaction = new Transaction(date, description, value, "unassigned name" , category);
                transactions.add(transaction);
                
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing data: " + e.getMessage());
        }

        return transactions;
    }
}

