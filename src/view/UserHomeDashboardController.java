package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import view3.JobangebotAnzeige;

public class UserHomeDashboardController {

	@FXML
	private ImageView homebutton;

	@FXML
	private Label name;

	@FXML
	private BorderPane results;

	@FXML
	private ImageView profilbutton;

	@FXML
	private ImageView searchbutton;

	@FXML
	private ImageView settingsbutton;

	@FXML
	private ImageView signoutbutton;

	@FXML
	private ImageView userpicture;

	private JobangebotAnzeige[] result = new JobangebotAnzeige[10];

	public void initialize() {
		// Label test = new Label();
		// Label test2 = new Label();
		// test2.setText("slfjhslklkfhj");
		// test.setText("Labelsdfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
		// test.setFont(new Font("Arial",100));
		// test.setLayoutX(-60);
		// name.setText("Test");

		// results.getChildren().add(test);
		// results.getChildren().add(test2);
		GridPane tp = new GridPane();
		for (int i = 0; i < 10; i++) {
			result[i] = new JobangebotAnzeige();
			// result[i].setLayoutX(20);
			// result[i].setLayoutY(67);
			tp.add(result[i], i % 4, i / 4);
		}

		ScrollPane sp = new ScrollPane();

		sp.setContent(tp);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setFitToHeight(true);
		results.setCenter(sp);
	}

	@FXML
	void openHome(MouseEvent event) {

	}

	@FXML
	void openProfil(MouseEvent event) {

	}

	@FXML
	void openSearch(MouseEvent event) {

	}

	@FXML
	void openSettings(MouseEvent event) {

	}

	@FXML
	void openSignOut(MouseEvent event) {

	}

	@FXML
	void showresult1(MouseEvent event) {

	}

}
