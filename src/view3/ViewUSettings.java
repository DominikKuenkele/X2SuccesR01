package view3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Verwaltung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Nutzer;

public class ViewUSettings implements Initializable{
	
	ObservableList<String> GenderList = FXCollections.observableArrayList("Männlich", "Weiblich", "Anderes");

    @FXML
    private ImageView homebutton;

    @FXML
    private ImageView profilbutton;

    @FXML
    private ImageView searchbutton;

    @FXML
    private ImageView settingsbutton;

    @FXML
    private ImageView signoutbutton;

    @FXML
    private ImageView addofferbutton;

    @FXML
    private TextField newprenom;

    @FXML
    private TextField newname;

    @FXML
    private TextField city;

    @FXML
    private TextField street;

    @FXML
    private TextField plz;

    @FXML
    private TextField streetnumber;

    @FXML
    private Button changeUserDataButton;

    @FXML
    private TextField email;

    @FXML
    private PasswordField oldpassword;

    @FXML
    private PasswordField newpassword;

    @FXML
    private PasswordField newpassword2;

    @FXML
    private Button changepwbutton;
    

    @FXML
    private DatePicker birthdate;


    @FXML
    private ChoiceBox<String> newgender;

	void changescene(String fxmlname) throws IOException {

		// schliesst aktuelles Fenster
		Stage stage2 = (Stage) homebutton.getScene().getWindow();
		stage2.close();

		Stage stage = new Stage();
		stage.setTitle("X2Success");
		Pane myPane = null;
		myPane = FXMLLoader.load(getClass().getResource(fxmlname));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void openHome(MouseEvent event) throws IOException {

		// Abfrage ob es schon Favoriten gibt
		if (true)
			changescene("User_Home_Dashboard_nofavs.fxml");
		else
			changescene("User_Home_Dashboard_favs.fxml");
	}

    @FXML
    void changePW(ActionEvent event) {
    	if (newpassword.getText().equals(newpassword2.getText())){
    		
    	}
    	
    }

    @FXML
    void changeUserData(ActionEvent event) {
    	Verwaltung v = new Verwaltung();
		Nutzer nutzer = v.getCurrentNutzer();//Daten ausfüllen
		//Setter Methoden
		String newprenom1 = newprenom.getText();
		String newname1 = newname.getText();
		String city1 = city.getText();
		String street1 = street.getText();

		// int plz1=Integer.parseInt(plz.getText());
		// int streetnumber1=Integer.parseInt(streetnumber.getText());
		String email1 = email.getText();
		String gender = newgender.getValue();

    }

    @FXML
    void createnweoffer(MouseEvent event) throws IOException {
    	changescene("Unternehmen_createoffer.fxml");
    }


    @FXML
    void openProfil(MouseEvent event) throws IOException {
    	changescene("Unternehmen_Profil.fxml");
    }

    @FXML
    void openSearch(MouseEvent event) throws IOException {
    	changescene("Unternehmen_Suche_dashboard.fxml");
    }

    @FXML
    void openSettings(MouseEvent event) throws IOException {
    	changescene("Unternehmen_Settings_Dashboard.fxml");
    }

    @FXML
    void openSignOut(MouseEvent event) throws IOException {
    	changescene("Einloggen.fxml");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Verwaltung v = new Verwaltung();
		Nutzer nutzer = v.getCurrentNutzer();//Daten ausfüllen
		newprenom.setText(nutzer.getFirstName());
		newname.setText(nutzer.getLastName());
		city.setText(nutzer.getAddress().getCity());
		street.setText(nutzer.getAddress().getStrasse());
		plz.setText(nutzer.getAddress().getPlz());
		streetnumber.setText(nutzer.getAddress().getNumber());
		email.setText(nutzer.geteMail());
		birthdate.setValue(nutzer.getBirthdate());
		
		
		
		newprenom.setText(nutzer.getFirstName());
		newgender.setValue("Geschlecht auswählen"); // Anfangswert
		newgender.setItems(GenderList); // Name der Liste
		
	}

}
