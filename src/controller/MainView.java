package controller;

import application.Verwaltung;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainView extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Verwaltung v = Verwaltung.getInstance();
			v.login("olaf.muelle@hsdf.de", "1234");
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/UProfil.fxml"));
			// AnchorPane root = (AnchorPane)
			// FXMLLoader.load(getClass().getResource("/view/Startseite.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
