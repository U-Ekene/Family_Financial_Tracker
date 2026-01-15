//Contributing authors: U Okoronkwo, S Nwachukwu, E Unaigwe, B Oladokun
package team6.fft.datastore;

import org.junit.jupiter.api.Test;
import team6.fft.model.Transaction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CSVTransactionReaderTest {
    
	//Contributing authors: U Okoronkwo, S Nwachukwu, E Unaigwe, B Oladokun
    @Test
    void testReadTransactions_validFile() throws IOException {
        // Create a temporary file and write mock data
        File tempFile = File.createTempFile("transactions", ".csv");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.append("12/01/2024,Purchase at Store,100.50,,\n");
            writer.append("12/02/2024,Deposit,,50.00,\n");
            writer.append("12/03/2024,Online Payment,,100.00,\n");
        }

        // Call the method to read transactions
        ArrayList<Transaction> transactions = CSVTransactionReader.readTransactions(tempFile.getAbsolutePath());

        // Check the number of transactions read
        assertEquals(3, transactions.size(), "There should be 3 transactions.");

        // Check the details of the first transaction
        Transaction t1 = transactions.get(0);
        assertEquals("Purchase at Store", t1.getDescription());
        assertEquals(-100.50, t1.getValue());
    }
    
    //Contributing authors: U Okoronkwo. S Nwachukwu, E Unaigwe, B Oladokun
    @Test
    void testReadTransactions_emptyFile() throws IOException {
        // Create an empty file
        File emptyFile = File.createTempFile("empty", ".csv");

        // Call the method to read transactions
        ArrayList<Transaction> transactions = CSVTransactionReader.readTransactions(emptyFile.getAbsolutePath());

        // Assert that no transactions are read
        assertTrue(transactions.isEmpty(), "The file is empty, so no transactions should be read.");
    }
     
    //Contributing authors: U Okoronkwo. S Nwachukwu, E Unaigwe, B Oladokun
    @Test
    void testReadTransactions_invalidFile() throws IOException {
        // Create a file with invalid data (missing value)
        File invalidFile = File.createTempFile("invalid", ".csv");
        try (FileWriter writer = new FileWriter(invalidFile)) {
            writer.append("12/01/2024,Valid Entry,100.50,,\n");
            writer.append("12/02/2024,Missing Debit or Credit,,\n");
        }

        // Call the method to read transactions
        ArrayList<Transaction> transactions = CSVTransactionReader.readTransactions(invalidFile.getAbsolutePath());

        // Assert that only 1 valid transaction is read
        assertEquals(1, transactions.size(), "Only one valid transaction should be read.");
    }
}
