package team6.fft.model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import team6.fft.datastore.Categories;
import team6.fft.model.Transaction;
import javafx.collections.FXCollections;
//Contributing authors:  S Nwachukwu, E Unaigwe
 public class TransactionsModel {
    
    private final StringProperty bankStatementProperty;
    private final StringProperty assignTransactionProperty;
    private ArrayList<Transaction> transactionList;
    private final ObservableList<Transaction> observableTransactionList;
    private Categories categories;
    public TransactionsModel() {
        bankStatementProperty = new SimpleStringProperty();
        assignTransactionProperty = new SimpleStringProperty();
        transactionList = new ArrayList<>();
        observableTransactionList = FXCollections.observableArrayList(transactionList);
        
    }
    public void setBankStatement(String filename) {
        bankStatementProperty.set(filename);
    }
    public StringProperty bankStatementProperty() {
        return bankStatementProperty;
    }
    public void setTransactionList(List<Transaction> list) {
    	 transactionList = new ArrayList<>(list);
    }
    public ArrayList<Transaction> getTransactionList(){
    	return new ArrayList<>(transactionList);
    }
    public ObservableList<Transaction> getObservableTransactionList(){
    	return observableTransactionList;
    }
    
    public void setAssignTransaction(String transaction) {
        assignTransactionProperty.set(transaction);
    }
    public StringProperty assignTransactionProperty() {
        return assignTransactionProperty;
    }
    
    public boolean autoAssignTransactions() {
    	return true;
    }
}
