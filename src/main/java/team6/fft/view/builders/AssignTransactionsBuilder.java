package team6.fft.view.builders;

import java.io.File;
import java.util.ArrayList;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import team6.fft.datastore.Buyer;
import team6.fft.datastore.LedgerGenerator;
import team6.fft.model.Transaction;
import team6.fft.view.controllers.Builder;
//Contributing authors: S Nwachukwu, E Unaigwe
public class AssignTransactionsBuilder implements Builder<Region> {

    private StringProperty assignTransactionProperty;
    private ObservableList<Transaction> transactionList;  // Use generic type
    private Runnable assignHandler;
    private TableView<Transaction> tableView;
    private  ObservableList<String> buyers = FXCollections.observableArrayList(
            "bolu", "michelle", "jack" 
    );
    private  ObservableList<String> categories = FXCollections.observableArrayList(
            "Retail", "Entertainment", "Groceries" 
    );
    private ComboBox<String> catBox = new ComboBox<>(categories);
	 private ComboBox<String> comboBox = new ComboBox<>(buyers);

    public AssignTransactionsBuilder(StringProperty assignTransactionProperty, ObservableList<Transaction> transactionList, Runnable assignHandler) {
        this.assignTransactionProperty = assignTransactionProperty;
        this.transactionList = transactionList;
        this.assignHandler = assignHandler;
    }

    public Region build() {
        GridPane results = new GridPane();
        results.setId("load-pane");
        results.getStylesheets().add(getClass().getResource("../../styles/styles.css").toExternalForm());
        results.add(transactionsText(), 0, 4);
        results.add(unassignedField(), 0, 5);
        results.add(assignButton(), 0, 6);
        results.add(unAssignButton(),0,7);
        results.add(sortCat(), 0,8);
        results.add(sortBuyer(), 0,9);
        
        
        results.add(ledgerButton(), 0, 10);
        GridPane selectBuyer = new GridPane();
        selectBuyer.add(label(), 0, 0);
        selectBuyer.add(dropBox(), 0, 1);
        selectBuyer.add(removeButton(), 0, 2);
        selectBuyer.add(addButton(), 0, 3);
        results.add(selectBuyer, 1, 5);
        
        GridPane selectCategory = new GridPane(10,10);
        selectCategory.add(labelCat(), 2,  0);
        selectCategory.add(dropDown(), 2, 1);
        selectCategory.add(removeCat(), 2, 2);
        selectCategory.add(addCat(), 2, 3);
        
        results.add(selectCategory, 2, 5);
        
        return results;
    }

    private Node transactionsText() {
        Label results = new Label("Unassigned Transactions");
        results.getStyleClass().add("label");
        return results;
    }

    private Node unassignedField() {
        tableView = new TableView<>(transactionList);

        // Define Columns
        TableColumn<Transaction, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Transaction, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Transaction, Double> amountColumn = new TableColumn<>("Value");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        TableColumn<Transaction, String> buyerColumn = new TableColumn<>("Buyer");
        buyerColumn.setCellValueFactory(new PropertyValueFactory<>("buyer"));

        TableColumn<Transaction, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        tableView.getColumns().addAll(dateColumn, descriptionColumn, amountColumn, buyerColumn, categoryColumn);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        tableView.setPrefWidth(500);
        tableView.setPrefHeight(400);

        return tableView;
    }
    private Node sortCat() {
        Button sortButton = new Button("Sort by Category");

        sortButton.setOnAction(event -> {
            // Get the category column dynamically
            TableColumn<Transaction, String> categoryColumn = null;

            for (TableColumn<Transaction, ?> column : tableView.getColumns()) {
                if ("Category".equals(column.getText())) {
                    categoryColumn = (TableColumn<Transaction, String>) column;
                    break;
                }
            }

            if (categoryColumn != null) {
                // Set the sort type and apply sorting
                categoryColumn.setSortType(TableColumn.SortType.ASCENDING); // Default sorting order
                tableView.getSortOrder().clear(); // Clear any previous sort order
                tableView.getSortOrder().add(categoryColumn); // Apply sorting
            }
        });

        return sortButton;
    } 
    
    private Node sortBuyer() {
        Button sortButton = new Button("Sort by Buyer");

        sortButton.setOnAction(event -> {
            
            TableColumn<Transaction, String> buyerColumn = null;

            for (TableColumn<Transaction, ?> column : tableView.getColumns()) {
                if ("Buyer".equals(column.getText())) {
                	buyerColumn = (TableColumn<Transaction, String>) column;
                    break;
                }
            }

            if (buyerColumn != null) {
                // Set the sort type and apply sorting
            	buyerColumn.setSortType(TableColumn.SortType.ASCENDING); // Default sorting order
                tableView.getSortOrder().clear(); // Clear any previous sort order
                tableView.getSortOrder().add(buyerColumn); // Apply sorting
            }
        });

        return sortButton;
    }
    
    private Node assignButton() {
        Button results = new Button("Assign");
        results.setId("assign");

        results.setOnAction(event -> {
        	
            ArrayList<Transaction> selectedTransactions = getSelectedTransactions();
            
            // Handle the selected transactions
            if(getSelectedBuyer() != null) {
	            for (int i = 0; i < selectedTransactions.size(); i++) {
	            	selectedTransactions.get(i).setBuyer(getSelectedBuyer());
	            }
            }
            
            if(getSelectedCategory() != null) {
            	for (int i = 0; i < selectedTransactions.size(); i++) {
                	if(selectedTransactions.get(i).getCategory() == "unassigned") {
	            		selectedTransactions.get(i).setCategory(getSelectedCategory());
                	}
	            }
            }
            tableView.refresh();
            System.out.println("Selected Transactions: " + selectedTransactions);
            assignHandler.run(); // Call the assign handler if needed
        });

        return results;
    }
    private Node unAssignButton() {
        Button results = new Button("Unassign");
        results.setId("unassign");

        results.setOnAction(event -> {
        	
            ArrayList<Transaction> selectedTransactions = getSelectedTransactions();
            
            // Handle the selected transactions
            if(getSelectedBuyer() != "unassigned") {
	            for (int i = 0; i < selectedTransactions.size(); i++) {
	            	selectedTransactions.get(i).setBuyer("unassigned");
	            }
            }
            
            if(getSelectedCategory() != "unassigned") {
            	for (int i = 0; i < selectedTransactions.size(); i++) {
                	if(selectedTransactions.get(i).getCategory() != "unassigned") {
	            		selectedTransactions.get(i).setCategory("unassigned");
                	}
	            }
            }
            tableView.refresh();
            System.out.println("Selected Transactions: " + selectedTransactions);
            assignHandler.run(); // Call the assign handler if needed
        });

        return results;
    }


    // Method to retrieve selected transactions
    public ArrayList<Transaction> getSelectedTransactions() {
        ObservableList<Transaction> selectedItems = tableView.getSelectionModel().getSelectedItems();
        return new ArrayList<>(selectedItems);
    }
    
    private Node removeButton() {
		Button removeButton = new Button("Remove Buyer");

       
        removeButton.setOnAction(event -> {
            String selectedBuyer = comboBox.getValue();
            if (selectedBuyer != null && buyers.contains(selectedBuyer)) {
                buyers.remove(selectedBuyer); 
                comboBox.setValue(null); 
            }
        });
		return removeButton;
	}
	
	 private Node addButton() {
			Button addButton = new Button("Add Buyer");

	       
			addButton.setOnAction(event -> {
		         String newBuyer = comboBox.getEditor().getText();
		         if (!buyers.contains(newBuyer) && !newBuyer.isBlank()) {
		             buyers.add(newBuyer); 
		             comboBox.setValue(newBuyer); 
		         }
		     });
			return addButton;
		}
	
	private Node dropBox() {
		
	        comboBox.setEditable(true); 
	        return comboBox;
	}
	
	private Node label() {
		Label result = new Label("Select Buyer:");
		return result;
	}
	
	private Node labelCat() {
		Label result = new Label("Select Category:");
		return result;
	}
	 private Node addCat() {
			Button addButton = new Button("Add Category");

	       
			addButton.setOnAction(event -> {
		         String newCategory = catBox.getEditor().getText();
		         if (!categories.contains(newCategory) && !newCategory.isBlank()) {
		        	 categories.add(newCategory); 
		             catBox.setValue(newCategory); 
		         }
		     });
			return addButton;
	  }
	 
	  private Node removeCat() {
			Button removeButton = new Button("Remove Category");

	       
	        removeButton.setOnAction(event -> {
	            String selectedCategory = catBox.getValue();
	            if (selectedCategory != null && categories.contains(selectedCategory)) {
	            	categories.remove(selectedCategory); 
	                catBox.setValue(null); 
	            }
	        });
			return removeButton;
		}
	  
	    private Node dropDown() {
			
	        catBox.setEditable(true); 
	        return catBox;
	    }
		
	    public String getSelectedBuyer() {
	    	return comboBox.getValue();
	    } 
	  
	private Node ledgerButton() {
	    Button results = new Button("Generate Ledger");
	    results.setId("assign");

	    results.setOnAction(event -> {
	        // Open a file chooser to let the user select the file path
	        FileChooser fileChooser = new FileChooser();
	        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
	        fileChooser.setInitialFileName("ledger.xlsx");

	        // Show the save file dialog
	        File file = fileChooser.showSaveDialog(new Stage());
	        if (file != null) {
	            try {
	                // Create a new instance of MonthlyLedgerGenerator and pass the transactions
	                LedgerGenerator ledgerGenerator = new LedgerGenerator(transactionList);
	                // Generate the ledger and save it to the selected file path
	                ledgerGenerator.generateLedger(file.getAbsolutePath());

	                // Show success alert
	                showAlert(Alert.AlertType.INFORMATION, "Success", "Ledger generated successfully!");

	            } catch (Exception e) {
	                // Show error alert if something goes wrong
	                showAlert(Alert.AlertType.ERROR, "Error", "Failed to generate ledger: " + e.getMessage());
	            }
	        } else {
	            // Show warning alert if no file is selected
	            showAlert(Alert.AlertType.WARNING, "Warning", "No file selected.");
	        }
	    });

	    return results;
	}

	// Helper method to show alerts
	private void showAlert(Alert.AlertType alertType, String title, String message) {
	    Alert alert = new Alert(alertType);
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}
	public String getSelectedCategory() {
        return catBox.getValue();
    } 
}
