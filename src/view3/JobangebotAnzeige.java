/**
 * 
 */
package view3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

/**
 * @author domin
 *
 */
public class JobangebotAnzeige extends AnchorPane {

	private ImageView imgImageView;
	private Label nameLabel;
	private Label gruendungLabel;
	private Label mitarbeiterLabel;
	private Label beschreibungLabel;
	private Label anforderungenLabel;
	private Label wochenstundenLabel;
	private Label gehaltLabel;

	private final int SIZE_IMAGE = 70;
	private final int LOC_X_IMG = 20;
	private final int LOC_Y_IMG = 20;
	private final int LOC_X_NAME = LOC_X_IMG + SIZE_IMAGE + 5;
	private final int LOC_Y_NAME = LOC_Y_IMG;
	private final int LOC_X_DESC = LOC_X_IMG + 15;
	private final int LOC_Y_DESC = LOC_Y_IMG + SIZE_IMAGE + 30;
	private final int DIST_Y = 25;
	private final String EMPH_FONT = "System Bold";
	private final int EMPH_SIZE = 14;

	private final String GRUENDUNG_TEXT = "Gründung: ";
	private final String MITARBEITER_TEXT = "Mitarbeiter: ";
	private final String ANFORDERUNG_TEXT = "Anforderung: ";
	private final String WOCHENSTUNDEN_EINHEIT = " Stunden/Woche";
	private final String GEHALT_EINHEIT = " EURO/Monat";

	/**
	 * 
	 */
	public JobangebotAnzeige() {
		super();
		setMinWidth(220);
		setMinHeight(260);
		try {
			imgImageView = new ImageView();
			imgImageView.setFitHeight(SIZE_IMAGE);
			imgImageView.setFitWidth(SIZE_IMAGE);
			imgImageView.setLayoutX(LOC_X_IMG);
			imgImageView.setLayoutY(LOC_Y_IMG);
			imgImageView.setPickOnBounds(true);
			imgImageView.setPreserveRatio(true);
			InputStream inputStream = new FileInputStream("src/view/Icons/Bk.png");
			Image img = new Image(inputStream);
			imgImageView.setImage(img);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

		nameLabel = new Label("BurgerKing");
		nameLabel.setLayoutX(LOC_X_NAME);
		nameLabel.setLayoutY(LOC_Y_NAME);
		nameLabel.setFont(new Font(EMPH_FONT, EMPH_SIZE));

		gruendungLabel = new Label(GRUENDUNG_TEXT);
		gruendungLabel.setLayoutX(LOC_X_NAME);
		gruendungLabel.setLayoutY(nameLabel.getLayoutY() + DIST_Y);

		mitarbeiterLabel = new Label(MITARBEITER_TEXT);
		mitarbeiterLabel.setLayoutX(LOC_X_NAME);
		mitarbeiterLabel.setLayoutY(gruendungLabel.getLayoutY() + DIST_Y);

		beschreibungLabel = new Label("Burgerbrater");
		beschreibungLabel.setLayoutX(LOC_X_DESC);
		beschreibungLabel.setLayoutY(LOC_Y_DESC);
		beschreibungLabel.setFont(new Font(EMPH_FONT, EMPH_SIZE));

		anforderungenLabel = new Label(ANFORDERUNG_TEXT);
		anforderungenLabel.setLayoutX(LOC_X_DESC);
		anforderungenLabel.setLayoutY(beschreibungLabel.getLayoutY() + DIST_Y);

		wochenstundenLabel = new Label(WOCHENSTUNDEN_EINHEIT);
		wochenstundenLabel.setLayoutX(LOC_X_DESC);
		wochenstundenLabel.setLayoutY(anforderungenLabel.getLayoutY() + DIST_Y);

		gehaltLabel = new Label(GEHALT_EINHEIT);
		gehaltLabel.setLayoutX(LOC_X_DESC);
		gehaltLabel.setLayoutY(wochenstundenLabel.getLayoutY() + DIST_Y);

		setCursor(Cursor.HAND);

		getChildren().add(imgImageView);
		getChildren().add(nameLabel);
		getChildren().add(gruendungLabel);
		getChildren().add(mitarbeiterLabel);
		getChildren().add(anforderungenLabel);
		getChildren().add(beschreibungLabel);
		getChildren().add(wochenstundenLabel);
		getChildren().add(gehaltLabel);
	}

	public void setName(String name) {
		nameLabel.setText(name);
	}

	public void setGruendung(String gruendung) {
		gruendungLabel.setText(GRUENDUNG_TEXT + gruendung);
	}

	public void setMitarbieter(String mitarbeiter) {
		mitarbeiterLabel.setText(MITARBEITER_TEXT + mitarbeiter);
	}

	public void setBeschreibung(String beschreibung) {
		beschreibungLabel.setText(beschreibung);
	}

	public void setAnforderungen(String anforderungen) {
		anforderungenLabel.setText(ANFORDERUNG_TEXT + anforderungen);
	}

	public void setWochenstunden(String wochenstunden) {
		wochenstundenLabel.setText(wochenstunden + WOCHENSTUNDEN_EINHEIT);
	}

	public void setGehalt(String gehalt) {
		gehaltLabel.setText(gehalt + GEHALT_EINHEIT);
	}
}
