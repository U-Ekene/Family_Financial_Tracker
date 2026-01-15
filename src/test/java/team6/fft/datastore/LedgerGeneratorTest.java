//Contributing author: S Nwachukwu
package team6.fft.datastore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team6.fft.model.Transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LedgerGeneratorTest {

    private ObservableList<Transaction> mockTransactions;
    private String tempFilePath;
    private File generatedFile;

    //Contributing author: S Nwachukwu
    @BeforeEach
    public void setUp() {
        // Set up mock data
        mockTransactions = FXCollections.observableArrayList(
            new Transaction(LocalDate.of(2024, 1, 15), "Grocery Store", 100.50, "John", "Groceries"),
            new Transaction(LocalDate.of(2024, 1, 20), "Book Store", 45.30, "Doe", "Books"),
            new Transaction(LocalDate.of(2024, 2, 5), "Coffee Shop", 10.25, "Alice", "Food"),
            new Transaction(LocalDate.of(2024, 2, 18), "Gas Station", 50.75, "Bob", "Fuel"),
            new Transaction(LocalDate.of(2024, 3, 10), "Clothing Store", 200.00, "Mary", "Clothing")
        );

        tempFilePath = "test_ledger.xlsx";
        generatedFile = new File(tempFilePath);
    }

    //Contributing author: S Nwachukwu
    @AfterEach
    public void tearDown() {
        // Clean up after tests
        if (generatedFile.exists()) {
            generatedFile.delete();
        }
    }
    //Contributing author: S Nwachukwu
    @Test
    public void testGenerateLedger_FileCreation() {
        LedgerGenerator ledgerGenerator = new LedgerGenerator(mockTransactions);
        ledgerGenerator.generateLedger(tempFilePath);

        assertTrue(generatedFile.exists(), "Ledger file should be created");
    }
    
    //Contributing author: S Nwachukwu
    @Test
    public void testGenerateLedger_CorrectSheets() throws IOException {
        LedgerGenerator ledgerGenerator = new LedgerGenerator(mockTransactions);
        ledgerGenerator.generateLedger(tempFilePath);

        try (FileInputStream fis = new FileInputStream(tempFilePath);
             var workbook = WorkbookFactory.create(fis)) {

            // Check if the correct sheets were created
            assertNotNull(workbook.getSheet("2024-01"), "Sheet for January 2024 should exist");
            assertNotNull(workbook.getSheet("2024-02"), "Sheet for February 2024 should exist");
            assertNotNull(workbook.getSheet("2024-03"), "Sheet for March 2024 should exist");
            assertNull(workbook.getSheet("2024-04"), "Sheet for April 2024 should not exist");
        }
    }
    
    //Contributing author: S Nwachukwu
    @Test
    public void testGenerateLedger_CorrectRowCount() throws IOException {
        LedgerGenerator ledgerGenerator = new LedgerGenerator(mockTransactions);
        ledgerGenerator.generateLedger(tempFilePath);

        try (FileInputStream fis = new FileInputStream(tempFilePath);
             var workbook = WorkbookFactory.create(fis)) {

            var januarySheet = workbook.getSheet("2024-01");
            var februarySheet = workbook.getSheet("2024-02");

            // Header row + 2 data rows
            assertEquals(3, januarySheet.getPhysicalNumberOfRows(), "January sheet should have 3 rows");
            // Header row + 2 data rows
            assertEquals(3, februarySheet.getPhysicalNumberOfRows(), "February sheet should have 3 rows");
        }
    }

    //Contributing author: S Nwachukwu
    @Test
    public void testGenerateLedger_EmptyTransactionList() {
        // Test with empty transaction list
        ObservableList<Transaction> emptyTransactions = FXCollections.observableArrayList();
        LedgerGenerator ledgerGenerator = new LedgerGenerator(emptyTransactions);
        ledgerGenerator.generateLedger(tempFilePath);

        assertTrue(generatedFile.exists(), "File should still be created even if there are no transactions");

        try (FileInputStream fis = new FileInputStream(tempFilePath);
             var workbook = WorkbookFactory.create(fis)) {
            assertEquals(0, workbook.getNumberOfSheets(), "Workbook should have no sheets for an empty transaction list");
        } catch (IOException e) {
            fail("Exception should not occur during validation");
        }
    }
    //Contributing author: S Nwachukwu
    @Test
    public void testGenerateLedger_NullFilePath() {
        LedgerGenerator ledgerGenerator = new LedgerGenerator(mockTransactions);

        assertThrows(NullPointerException.class, () -> ledgerGenerator.generateLedger(null), 
            "Should throw NullPointerException for null file path");
    }
}
