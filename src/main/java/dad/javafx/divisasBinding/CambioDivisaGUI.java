package dad.javafx.divisasBinding;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import dad.common.*;

public class CambioDivisaGUI extends Application {

	/* Declaración JavaFX */
	private TextField txtFrom;
	private TextField txtTo;
	
	/* Declaración divisas */
	private Divisa euro = new Divisa("Euro", 1);
	private Divisa libra = new Divisa("Libra", 0.8873);
	private Divisa dolar = new Divisa("Dolar", 1.2007);
	private Divisa yen = new Divisa("Yen", 133.59);
	
	private ComboBox<Divisa> selectFrom;
	private ComboBox<Divisa> selectTo;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		txtFrom = new TextField("0");
		
		txtTo = new TextField("0");
		txtTo.setEditable(false);
		
		selectFrom = new ComboBox<>();
		selectFrom.getItems().addAll(euro, libra, dolar, yen);
		selectFrom.getSelectionModel().selectFirst();
		
		selectTo = new ComboBox<>();
		selectTo.getItems().addAll(euro, libra, dolar, yen);	
		selectTo.getSelectionModel().selectFirst();
		
		// Ahora aplicamos bind usando nuestro objeto
		txtTo.textProperty().bind(new DivisaBinding(txtFrom.textProperty(), selectFrom.valueProperty(), selectTo.valueProperty()));
		
		HBox fromBox = new HBox(5, txtFrom, selectFrom);
		fromBox.setAlignment(Pos.CENTER);
		fromBox.setFillHeight(false);
		
		HBox toBox = new HBox(5, txtTo, selectTo);
		toBox.setAlignment(Pos.CENTER);
		toBox.setFillHeight(false);
		
		VBox root = new VBox(5, fromBox, toBox);
		root.setAlignment(Pos.CENTER);
		root.setFillWidth(false);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("Cambio de divisa");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);

	}

}
