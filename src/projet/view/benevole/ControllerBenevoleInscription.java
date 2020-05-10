package projet.view.benevole;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import jfox.javafx.view.IManagerGui;
import projet.data.Compte;
import projet.view.EnumView;


public class ControllerBenevoleInscription {
	

	// Composants de la vue
	
	@FXML
	private TextField		fieldPseudo;
	@FXML
	private PasswordField	fieldMotDePasse;

	
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
