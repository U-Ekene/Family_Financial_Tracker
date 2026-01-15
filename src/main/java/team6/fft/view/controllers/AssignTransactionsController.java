package team6.fft.view.controllers;

import java.util.ArrayList;

import javafx.scene.layout.Region;
import team6.fft.model.Transaction;
import team6.fft.model.TransactionsModel;
import team6.fft.view.builders.AssignTransactionsBuilder;

//Contributing authors:  S Nwachukwu, E Unaigwe
public class AssignTransactionsController {
	private Builder<Region> builder;
	private TransactionsModel model;
	
	//Contributing authors:  S Nwachukwu, E Unaigwe	
	public AssignTransactionsController(TransactionsModel model) {
		
		this.model = model;
		this.builder = new AssignTransactionsBuilder(model.assignTransactionProperty(),model.getObservableTransactionList() ,this::assignTransactions);
	} 
	//Contributing authors:  S Nwachukwu, E Unaigwe
	public Region getView() {
		return builder.build();
	}
	//Contributing authors:  S Nwachukwu, E Unaigwe
	public void assignTransactions() {
	}
	
}
