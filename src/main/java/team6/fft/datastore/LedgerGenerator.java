package team6.fft.datastore;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javafx.collections.ObservableList;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import team6.fft.model.Transaction;
//Contributing authors: S Nwachukwu
public class LedgerGenerator {

    private ObservableList<Transaction> transactions;
    public LedgerGenerator(ObservableList<Transaction> transactions) {
        this.transactions = transactions;
    }
    public void generateLedger(String filePath) {
        Workbook workbook = new XSSFWorkbook();

        // List of unique year-month combinations
        List<String> uniqueMonths = getUniqueMonths();

        for (String month : uniqueMonths) {
            Sheet sheet = workbook.createSheet(month);
            createHeaderRow(sheet);

            int rowNum = 1;
            for (Transaction transaction : transactions) {
                LocalDate date = transaction.getDate();
                String transactionMonth = date.getYear() + "-" + String.format("%02d", date.getMonthValue());

                if (transactionMonth.equals(month)) {
                    Row row = sheet.createRow(rowNum++);
                    fillTransactionRow(transaction, row);
                }
            }
        }

        // Write the workbook to the file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private List<String> getUniqueMonths() {
        List<String> uniqueMonths = new ArrayList<>();

        for (Transaction transaction : transactions) {
            LocalDate date = transaction.getDate();
            String month = date.getYear() + "-" + String.format("%02d", date.getMonthValue());

            if (!uniqueMonths.contains(month)) {
                uniqueMonths.add(month);
            }
        }

        return uniqueMonths;
    }
    private void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        String[] columns = {"Date", "Description", "Buyer", "Amount", "Category"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(getHeaderCellStyle(sheet.getWorkbook()));
        }
    }
    private void fillTransactionRow(Transaction transaction, Row row) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        row.createCell(0).setCellValue(transaction.getDate().format(formatter));
        row.createCell(1).setCellValue(transaction.getDescription());
        row.createCell(2).setCellValue(transaction.getBuyer());
        row.createCell(3).setCellValue(transaction.getValue());
        row.createCell(4).setCellValue(transaction.getCategory());
    }
    private CellStyle getHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }
}

