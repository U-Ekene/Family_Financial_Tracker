package team6.fft.datastore;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import team6.fft.model.Transaction;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//Contributing authors: U Okoronkwo, E Unaigwe
public class ExcelTransactionReader {
//Contributing authors: U Okoronkwo, E Unaigwe
	
	private static final AutoAssignCategories assign = new AutoAssignCategories();
	
    public static List<Transaction> readTransactions(String filePath) {
    	
        List<Transaction> transactions = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); 

            for (Row row : sheet) {
              
                Cell dateCell = row.getCell(0);
                Cell descriptionCell = row.getCell(1);
                Cell debitCell = row.getCell(2);
                Cell creditCell = row.getCell(3);
                

               
                if (dateCell == null || descriptionCell == null) {
                    continue;
                }

               
                LocalDate date = LocalDate.parse(dateCell.getStringCellValue(), formatter);

               
                String description = descriptionCell.getStringCellValue();

              
                double debit = debitCell == null ? 0 : debitCell.getNumericCellValue();
                double credit = creditCell == null ? 0 : creditCell.getNumericCellValue();
                double value = credit > 0 ? credit : -debit;
                
                String category = assign.getCategory(description);

                Transaction transaction = new Transaction(date, description, value, "unassigned name", category);
                transactions.add(transaction);
            }
        } catch (IOException e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing data: " + e.getMessage());
        }

        return transactions;
    }
}
