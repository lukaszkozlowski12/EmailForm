package application;
	
import java.awt.event.ActionEvent;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;



public class Main extends Application {
final Button sendButton = new Button("Send");
final Label notification = new Label();
final TextField subject = new TextField(" ");
final TextArea text = new TextArea("");

String adres = " ";



	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) {
	
		
		primaryStage.setTitle("Tworzenie wiadomości");
		Scene scene = new Scene(new Group(),600, 470);
		
		@SuppressWarnings("rawtypes")
		final ComboBox emailComboBox = new ComboBox();
		
		emailComboBox.getItems().addAll("l.kozlowski@onet.pl","poczta@wp.pl","adresmail@gmail.com");
		
		emailComboBox.setPromptText("Adresy email");
		emailComboBox.setEditable(true);
emailComboBox.setOnAction(arg0 ->{ 
	if(!emailComboBox.getSelectionModel().getSelectedItem().toString().isEmpty())
	adres=emailComboBox.getSelectionModel().getSelectedItem().toString();   } );	
		
		
@SuppressWarnings("rawtypes")
final ComboBox priorytetCombo = new ComboBox();
priorytetCombo.getItems().addAll("Wysoki","Normalny","Niski");
priorytetCombo.setValue("Normalny");

priorytetCombo.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

	@Override
	public ListCell<String> call(ListView<String> param) {
		
		final ListCell<String> cell = new ListCell<String>() {
			
			{
				
			super.setPrefWidth(100);	
			}
			@Override
			public void updateItem(String item, boolean empty) {
				
				super.updateItem(item, empty);
				
				if(item != null) {
					setText(item);
					
					if(item.contains("Wysoki")){setTextFill(Color.RED);
					}else if(item.contains("Niski")) { setTextFill(Color.GREEN);
					}else {setTextFill(Color.BLACK);}
					
					
				}else {  setText(null);}
			}
			
		};
	
		
		return cell;
	}

	
}
);


		sendButton.setOnAction(arg0 ->{
			if(emailComboBox.getValue() != null && !emailComboBox.getValue().toString().isEmpty() && !subject.getText().toString().isEmpty() && !text.getText().toString().isEmpty())
			{
				notification.setText("Widomosc wysłana do: "+adres);
				emailComboBox.setValue("");
			/*	if(priorytetCombo.getValue()!= null && !priorytetCombo.getValue().toString().isEmpty()) {
					
					priorytetCombo.setValue("");
				}*/
				subject.clear();
				text.clear();
				
			}	else { notification.setText("Proszę uzupełnic dane !!");}		
			
		} );
		
		Button btn = new Button("Dodaj tresc");
		GridPane grid = new GridPane();
		grid.setVgap(5);
		grid.setHgap(10);
		grid.setPadding(new Insets(5,5,5,5));
		grid.add(new Label("Odbiorca: "), 0, 0);
		grid.add(emailComboBox, 1, 0);
		//grid.add(new Label("Priorytet"), 2, 0);
//grid.add(priorytetCombo, 3, 0);
grid.add(new Label("Temat:"), 0, 1);
grid.add(subject,1 , 1,3,1);
grid.add(text, 0, 2, 4,1);
grid.add(sendButton, 0, 3);
grid.add(notification, 1, 3, 3, 1);
grid.add(btn, 4,3);

Group root = (Group)scene.getRoot();
root.getChildren().add(grid);
primaryStage.setScene(scene);
primaryStage.show();




		
		
		
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
