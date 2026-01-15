package team6.fft.view.builders;

import javafx.scene.layout.Region;
import team6.fft.view.controllers.Builder;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
//Contributing authors:  S Nwachukwu, E Unaigwe
public class BankStatementViewBuilder implements Builder<Region>{
	
	private StringProperty bankStatementProperty;
	private Runnable browseHandler;
	//Contributing authors:  S Nwachukwu, E Unaigwe
	public BankStatementViewBuilder(StringProperty bankStatementProperty, Runnable browseHandler) {
		this.bankStatementProperty = bankStatementProperty;
		this.browseHandler = browseHandler;
	}
	
	//Contributing authors:  S Nwachukwu, E Unaigwe
	@Override
	public Region build() {
		GridPane results = new GridPane(10, 10);
		results.setId("load-pane");
		results.setId("upload-pane");
		results.getStylesheets().add(getClass().getResource("../../styles/styles.css").toExternalForm());
		results.add(title(), 0, 0);
		results.add(statementField(), 0, 1);
		results.add(browseButton(), 1, 1);
		return results;
	}
	//Contributing authors:  S Nwachukwu, E Unaigwe
	private Node browseButton() {
		Button results = new Button("Browse");
		results.setId(String.format("browse"));
		results.setOnAction(evt->browseHandler.run());
		return results;
	}
	//Contributing authors:  S Nwachukwu, E Unaigwe
	private Node statementField() {
		TextField results = new TextField();
		results.setEditable(false);
		results.setPrefWidth(100);
		results.textProperty().bind(bankStatementProperty);
		return results;
	}
	//Contributing authors:  S Nwachukwu, E Unaigwe
	private Node title() {
		Label results = new Label("Upload Bank Statement");
		results.getStyleClass().add("label");
		return results;
	}
}
