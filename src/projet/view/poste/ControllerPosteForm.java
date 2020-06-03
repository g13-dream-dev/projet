package projet.view.poste;

import java.util.ArrayList;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalTimeStringConverter;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.view.IManagerGui;
import projet.data.Poste;
import projet.view.EnumView;

public class ControllerPosteForm {

	// Composants de la vue

	@FXML
	private TextField textFieldId;
	@FXML
	private TextField textFieldLibelle;
	@FXML
	private TextField textFieldNombrePlaces;
	@FXML
	private TextField textFieldHeureD;
	@FXML
	private ComboBox<String> comboBoxEtat;

	// Autres champs

	private Poste courant;

	@Inject
	private IManagerGui managerGui;
	@Inject
	private ModelPoste modelPoste;

	// Initialisation du Controller

	@FXML
	private void initialize() {

		courant = modelPoste.getCourant();

		// Data binding
		courant = modelPoste.getCourant();
		textFieldId.textProperty().bindBidirectional(courant.idProperty(), new ConverterStringInteger());
		textFieldLibelle.textProperty().bindBidirectional(courant.libelleProperty());
		textFieldNombrePlaces.textProperty().bindBidirectional(courant.nombrePlacesProperty(), new IntegerStringConverter());
		textFieldHeureD.textProperty().bindBidirectional(courant.heureDProperty(), new LocalTimeStringConverter());
		ObservableList<String> etats = FXCollections.observableArrayList();
		etats.add("Non débutée"); etats.add("En cours"); etats.add("Achevée");
		comboBoxEtat.setItems(etats);
		comboBoxEtat.valueProperty().bindBidirectional(courant.etatProperty());
	}

	// Actions

	@FXML
	private void doAnnuler() {
		managerGui.showView(EnumView.PosteListe);
	}

	@FXML
	private void doValider() {
		modelPoste.validerMiseAJour();
		managerGui.showView(EnumView.PosteListe);
	}

	//Méthodes de fonctionnalites
	
		@FXML
		private void doRechercherUnPoste(){
			modelPoste.actualiserListe();
			managerGui.showView(EnumView.PosteRechercher);
			
		}
		
		@FXML
		private void doListerTousLesPostes() {
			modelPoste.actualiserListe();
			managerGui.showView(EnumView.PosteListe);
		}
		
		@FXML
		private void doAjouterUnPoste() {
			modelPoste.preparerAjouter();
			managerGui.showView(EnumView.PosteForm);
		}
		
		@FXML
		private void doAttribuerDesPostesAuxBenevoles() {
			
		}

}
