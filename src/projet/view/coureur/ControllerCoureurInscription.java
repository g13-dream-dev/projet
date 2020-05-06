package projet.view.coureur;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import jfox.javafx.view.IManagerGui;
import projet.data.Compte;
import projet.view.EnumView;


public class ControllerCoureurInscription {
	

	// Composants de la vue
	
	@FXML
	private TextField		tfNom;
	@FXML
	private TextField		tfPrenom;
	@FXML
	private DatePicker dpNaissance;
	@FXML
	private TextField tfTelephone;
	@FXML
	private TextField tfAdresse;
	@FXML
	private TextField tfCodePostal;
	@FXML
	private TextField		tfEmail;
	@FXML
	private TextField tfClub;
	

	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	
	
	// Initialisation du Controller
	
	@FXML
	private void initialize() {
		
	}
	
	
	public void refresh() {
		
	}
	

	// Actions
	
	@FXML
	private void doInscrire() {
		
	}
	

}
