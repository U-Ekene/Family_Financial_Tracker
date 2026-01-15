package team6.fft.view.controllers;

import java.io.File;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import team6.fft.model.TransactionsModel;
import team6.fft.view.builders.BankStatementViewBuilder;
import team6.fft.datastore.CSVTransactionReader;
import team6.fft.datastore.ExcelTransactionReader;
//Contributing authors:  S Nwachukwu, E Unaigwe
public class BankStatementController {
	
	private Builder<Region> builder;
	private TransactionsModel model;
	//Contributing authors:  S Nwachukwu, E Unaigwe
	public BankStatementController(TransactionsModel model) {
		
		this.model = model;
		this.builder = new BankStatementViewBuilder(model.bankStatementProperty(), this::browseBankStatements);
	} 
	//Contributing authors:  S Nwachukwu, E Unaigwe
	public Region getView() {
		return builder.build();
	}
	//Contributing authors:  S Nwachukwu, E Unaigwe
	private void browseBankStatements() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose Bank Statement");
		
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		
		File selectedFile = fileChooser.showOpenDialog(null);
		
		if(selectedFile != null && (selectedFile.getName().endsWith(".xlsx") || selectedFile.getName().endsWith(".csv"))) {
			model.setBankStatement(selectedFile.getName());
			
			if(selectedFile.getName().endsWith(".csv")) {
				model.setTransactionList(CSVTransactionReader.readTransactions(selectedFile.getAbsolutePath()));
			}
			
			else if(selectedFile.getName().endsWith(".xlsx")) {
				model.setTransactionList(ExcelTransactionReader.readTransactions(selectedFile.getAbsolutePath()));
			}
			
			showInfoDialog("Transactions have been successfully read and stored!");
		}
		
		
		else {
			showErrorDialog("Invalid file type selected. Please choose a .csv or .xlsx file.");;
		}
		System.out.print(model.getTransactionList());
		model.getObservableTransactionList().setAll(model.getTransactionList());
		

	}
	private void showInfoDialog(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showErrorDialog(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}


