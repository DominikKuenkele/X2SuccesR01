package view2;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EinloggenController {

    @FXML
    private TextField EmailEingabe;

    @FXML
    private PasswordField PasswortEingabe;

    @FXML
    private Button LoginButton;

    void changescene(String fxmlname) throws IOException {
    	 
    	 //schliesst aktuelles Fenster
     	Stage stage2 = (Stage) LoginButton.getScene().getWindow();
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
    void Login(ActionEvent event) throws IOException {

    	//PW und Benutzernamen prüfen
    	if(true){ //Wenn Benutzer Freelancer ist
    		if (true)changescene("User_Home_Dashboards_nofavs.fxml"); //Wenn Benutzer Favoriten hat
    		else changescene("User_Home_Dashboards_favs.fxml");//Benutzer hat keine Favoriten
    	} else {
    		if (true)changescene("User_Home_Dashboards_nofavs.fxml"); //Wenn Benutzer Favoriten hat
    		else changescene("User_Home_Dashboards_favs.fxml");//Benutzer hat keine Favoriten
    	}
    }

}
