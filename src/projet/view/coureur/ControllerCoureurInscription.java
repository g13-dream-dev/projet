package projet.view.coureur;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.view.IManagerGui;
import projet.data.Compte;
import projet.data.Coureur;
import projet.data.Personne;
import projet.view.EnumView;
import projet.view.personne.ModelPersonne;


public class ControllerCoureurInscription {
	

	// Composants de la vue
	
	@FXML
	private TextField		tfId1;
	@FXML
	private TextField		tfNom1;
	@FXML
	private TextField		tfPrenom1;
	@FXML
	private DatePicker dpNaissance1;
	@FXML
	private TextField tfTelephone1;
	@FXML
	private TextField tfAdresse1;
	@FXML
	private TextField tfCodePostal1;
	@FXML
	private TextField		tfEmail1;
	@FXML
	private TextField tfClub1;
	

	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelCoureur		modelCoureur;
	
	// Initialisation du Controller
	
	@FXML
	private void initialize() {
		Coureur courant1 = modelCoureur.getCourant1();
		
		// Champs simples
		tfId1.textProperty().bindBidirectional( courant1.idProperty(), new ConverterStringInteger() );
	}
	
	
	public void refresh() {
		
	}
	

	// Actions
	
	@FXML
	private void doInscrire() {
		
	}
	

}
