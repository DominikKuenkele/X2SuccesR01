package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewStart {

	@FXML
	private Button aaa;

	@FXML
	private Button bbb;

	@FXML
	void go(ActionEvent event) throws IOException {
		Stage stage2 = (Stage) aaa.getScene().getWindow();
		stage2.close();

		Stage stage = new Stage();
		stage.setTitle("X2Success - Freelancer");
		Pane myPane = FXMLLoader.load(getClass().getResource("FRahmen.fxml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void go2(ActionEvent event) throws IOException {
		Stage stage2 = (Stage) bbb.getScene().getWindow();
		stage2.close();

		Stage stage = new Stage();
		stage.setTitle("X2Success - Unternehmen");
		Pane myPane = FXMLLoader.load(getClass().getResource("URahmen.fxml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();
	}
}
