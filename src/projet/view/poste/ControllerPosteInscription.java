package projet.view.poste;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Compte;
import projet.data.Coureur;
import projet.data.Personne;
import projet.view.EnumView;
import projet.view.personne.ModelPersonne;

public class ControllerPosteInscription {
	
	

	// Composants de la vue
	
	@FXML
	private TextField		idcoureur;
	@FXML
	private TextArea		libelle;
	@FXML
	private TextField		heuredeb;
	@FXML
	private TextField		nbplace;

	

	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelPoste		modelCoureur;
	
	// Initialisation du Controller
	
	@FXML
	private void initialize() {
		Coureur courant1 = modelCoureur.getCourant1();
		Coureur courant2 = modelCoureur.getCourant2();
		
		
		//courant1 === capitaine
		// Champs simples
		tfId1.textProperty().bindBidirectional( courant1.idProperty(), new ConverterStringInteger() );
		tfNom1.textProperty().bindBidirectional( courant1.nomProperty() );
		tfPrenom1.textProperty().bindBidirectional( courant1.prenomProperty() );
		tfTelephone1.textProperty().bindBidirectional(courant1.telephoneProperty());
		tfAdresse1.textProperty().bindBidirectional(courant1.adresseProperty());
		tfCodePostal1.textProperty().bindBidirectional(courant1.codePostalProperty(), new ConverterStringInteger());
		tfEmail1.textProperty().bindBidirectional(courant1.emailProperty());
		tfClub1.textProperty().bindBidirectional(courant1.clubProperty());
		brSexe1 = new ToggleGroup();
		rbHomme1.setToggleGroup(brSexe1);
		rbFemme1.setToggleGroup(brSexe1);
		
		
		//champ complex
		UtilFX.bindBidirectional(dpNaissance1.getEditor(), courant1.naissanceProperty(), new ConverterStringLocalDate());
		brSexe1.selectedToggleProperty().addListener(l ->{
			if(brSexe1.getSelectedToggle().equals(rbHomme1))courant1.setSexe("Homme");
			if(brSexe1.getSelectedToggle().equals(rbFemme1))courant1.setSexe("Femme");
		});
		
		//courant2 === equipier
		// Champs simples
		tfId2.textProperty().bindBidirectional( courant2.idProperty(), new ConverterStringInteger() );
		tfNom2.textProperty().bindBidirectional( courant2.nomProperty() );
		tfPrenom2.textProperty().bindBidirectional( courant2.prenomProperty() );
		tfTelephone2.textProperty().bindBidirectional(courant2.telephoneProperty());
		tfAdresse2.textProperty().bindBidirectional(courant2.adresseProperty());
		tfCodePostal2.textProperty().bindBidirectional(courant2.codePostalProperty(), new ConverterStringInteger());
		tfEmail2.textProperty().bindBidirectional(courant2.emailProperty());
		tfClub2.textProperty().bindBidirectional(courant2.clubProperty());
		brSexe2 = new ToggleGroup();
		rbHomme2.setToggleGroup(brSexe2);
		rbFemme2.setToggleGroup(brSexe2);
		
		//champ complex
		UtilFX.bindBidirectional(dpNaissance2.getEditor(), courant2.naissanceProperty(), new ConverterStringLocalDate());
		brSexe2.selectedToggleProperty().addListener(l ->{
			if(brSexe2.getSelectedToggle().equals(rbHomme2))courant2.setSexe("Homme");
			if(brSexe2.getSelectedToggle().equals(rbFemme2))courant2.setSexe("Femme");
		});
	}
	
	
	public void refresh() {
		
	}
	

	// Actions
	
	@FXML
	private void doInscrire() {
		if ( cbEngagement1.isSelected() && cbEngagement2.isSelected()) {
			modelCoureur.validerMiseAJour();
		}else {
			throw new ExceptionValidation("Vous devez accepter le reglement!");
		}
	}
	
}
