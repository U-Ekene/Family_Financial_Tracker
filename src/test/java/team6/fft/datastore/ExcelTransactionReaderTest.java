package team6.fft.datastore;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import team6.fft.model.Transaction;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExcelTransactionReaderTest {

    private static final String TEST_FILE = "test_transactions.xlsx";

    @Test
    void testReadTransactions_validFile() {
        // Create a valid Excel file without a header row
        try (Workbook workbook = new XSSFWorkbook()) {
            var sheet = workbook.createSheet("Transactions");

            // Add valid data rows
            var row1 = sheet.createRow(0); // Start from row 0 (no headers)
            row1.createCell(0).setCellValue("12/01/2024"); // Date
            row1.createCell(1).setCellValue("Groceries"); // Description
            row1.createCell(2).setCellValue(100.0); // Debit
            row1.createCell(3).setCellValue(0.0); // Credit

            var row2 = sheet.createRow(1);
            row2.createCell(0).setCellValue("12/02/2024"); // Date
            row2.createCell(1).setCellValue("Salary"); // Description
            row2.createCell(2).setCellValue(0.0); // Debit
            row2.createCell(3).setCellValue(500.0); // Credit

            try (FileOutputStream fos = new FileOutputStream(TEST_FILE)) {
                workbook.write(fos);
            }
        } catch (Exception e) {
            fail("Failed to create valid test file.");
        }

        // Read the transactions
        List<Transaction> transactions = ExcelTransactionReader.readTransactions(TEST_FILE);

        assertNotNull(transactions, "Transactions list should not be null.");
        assertEquals(2, transactions.size(), "Expected 2 transactions.");

        // Validate the transactions
        Transaction t1 = transactions.get(0);
        assertEquals(LocalDate.of(2024, 12, 1), t1.getDate(), "Date mismatch for first transaction.");
        assertEquals("Groceries", t1.getDescription(), "Description mismatch for first transaction.");
        assertEquals(-100.0, t1.getValue(), "Value mismatch for first transaction.");

        Transaction t2 = transactions.get(1);
        assertEquals(LocalDate.of(2024, 12, 2), t2.getDate(), "Date mismatch for second transaction.");
        assertEquals("Salary", t2.getDescription(), "Description mismatch for second transaction.");
        assertEquals(500.0, t2.getValue(), "Value mismatch for second transaction.");
    }

    @Test
    void testReadTransactions_invalidFile() {
        // Test reading a non-existent file
        List<Transaction> transactions = ExcelTransactionReader.readTransactions("non_existent_file.xlsx");

        assertNotNull(transactions, "Transactions list should not be null for a non-existent file.");
        assertTrue(transactions.isEmpty(), "Expected no transactions for a non-existent file.");
    }

    

    @Test
    void testReadTransactions_invalidDateFormat() {
        // Create an Excel file with an invalid date format
        try (Workbook workbook = new XSSFWorkbook()) {
            var sheet = workbook.createSheet("Transactions");

            // Add valid data rows
            var row = sheet.createRow(0); // Start from row 0 (no headers)
            row.createCell(0).setCellValue("Invalid Date"); // Invalid Date
            row.createCell(1).setCellValue("Description");
            row.createCell(2).setCellValue(100.0);

            try (FileOutputStream fos = new FileOutputStream(TEST_FILE)) {
                workbook.write(fos);
            }
        } catch (Exception e) {
            fail("Failed to create test file with an invalid date.");
        }

        // Read the transactions
        List<Transaction> transactions = ExcelTransactionReader.readTransactions(TEST_FILE);

        assertNotNull(transactions, "Transactions list should not be null.");
        assertTrue(transactions.isEmpty(), "Expected no transactions for invalid date.");
    }
}
