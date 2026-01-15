//Contributing authors:S Nwachukwu, E Unaigwe
package team6.fft;

import team6.fft.model.TransactionsModel;
import team6.fft.view.controllers.BankStatementController;
import team6.fft.view.controllers.AssignTransactionsController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		TransactionsModel model = new TransactionsModel();
		
		GridPane mainLayout = new GridPane(20, 20);
		mainLayout.setPadding(new Insets(10, 10, 10, 10));
		mainLayout.add(new BankStatementController(model).getView(), 0, 0);
		mainLayout.add(new AssignTransactionsController(model).getView(), 0, 1);
		
		

		
		Scene scene = new Scene(mainLayout, 600, 600);
		
		primaryStage.setTitle("Family Finance Tracker");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
