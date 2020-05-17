package projet.view.benevole;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Benevole;
import projet.data.Coureur;
import projet.data.Permis;
import projet.view.EnumView;
import projet.view.personne.ModelPersonne;

public class ControllerBenevoleForm {
	

	// Composants de la vue
	
	@FXML
	private TextField		tfId;
	@FXML
	private TextField		tfNom;
	@FXML 
	private TextField		tfPrenom;
	@FXML
	private ToggleGroup		brSexe;
	@FXML
	private RadioButton 	rbHomme;
	@FXML
	private RadioButton 	rbFemme;
	@FXML
	private DatePicker		dpNaissance;  
	@FXML
	private TextField		tfTelephone;
	@FXML
	private TextField		tfAdresse;
	@FXML
	private TextField		tfCodePostal;
	@FXML
	private TextField		tfEmail;
	@FXML
	private CheckBox		cbPermanent;
	@FXML
	private TextField		tfNumero;
	@FXML
	private DatePicker		dpDateDelivrance;  
	@FXML
	private CheckBox		cbEngagement;
	

	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelBenevole		modelBenevole;
	
	// Initialisation du Controller
	
	@FXML
	private void initialize() {
		Benevole courant = modelBenevole.getCourant();
		Permis courantPermis = modelBenevole.getCourantPermis();
		
		//courant1 === capitaine
		// Champs simples
		tfId.textProperty().bindBidirectional( courant.idProperty(), new ConverterStringInteger() );
		tfNom.textProperty().bindBidirectional( courant.nomProperty() );
		tfPrenom.textProperty().bindBidirectional( courant.prenomProperty() );
		tfTelephone.textProperty().bindBidirectional(courant.telephoneProperty());
		tfAdresse.textProperty().bindBidirectional(courant.adresseProperty());
		tfCodePostal.textProperty().bindBidirectional(courant.codePostalProperty(), new ConverterStringInteger());
		tfEmail.textProperty().bindBidirectional(courant.emailProperty());
		tfNumero.textProperty().bindBidirectional(courantPermis.numeroProperty());
		brSexe = new ToggleGroup();
		rbHomme.setToggleGroup(brSexe);
		rbFemme.setToggleGroup(brSexe);
		cbPermanent.selectedProperty().bindBidirectional( courant.permanentProperty() );
		
		//champ complex
		UtilFX.bindBidirectional(dpNaissance.getEditor(), courant.naissanceProperty(), new ConverterStringLocalDate());
		UtilFX.bindBidirectional(dpDateDelivrance.getEditor(), courantPermis.dateDelivranceProperty(), new ConverterStringLocalDate());
		brSexe.selectedToggleProperty().addListener(l ->{
			if(brSexe.getSelectedToggle().equals(rbHomme))courant.setSexe("Homme");
			if(brSexe.getSelectedToggle().equals(rbFemme))courant.setSexe("Femme");
		});
		
	}
	
	
	public void refresh() {
		
	}
	

	// Actions
	
	@FXML
	private void doInscrire() {
		if ( cbEngagement.isSelected()) {
			modelBenevole.validerMiseAJour();
		}else {
			throw new ExceptionValidation("Vous devez accepter le reglement!");
		}
	}
	
}
