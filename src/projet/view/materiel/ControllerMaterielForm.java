package projet.view.materiel;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.view.IManagerGui;
import projet.data.Materiel;
import projet.view.EnumView;

public class ControllerMaterielForm {

	// Composants de la vue

	@FXML
	private TextField textFieldId;
	@FXML
	private TextField textFieldNom;
	@FXML
	private TextField textFieldNombre;

	// Autres champs

	private Materiel courant;

	@Inject
	private IManagerGui managerGui;
	@Inject
	private ModelMateriel modelMateriel;

	// Initialisation du Controller

	@FXML
	private void initialize() {

		courant = modelMateriel.getCourant();

		// Data binding
		courant = modelMateriel.getCourant();
		textFieldId.textProperty().bindBidirectional(courant.idProperty(), new ConverterStringInteger());
		textFieldNom.textProperty().bindBidirectional(courant.nomProperty());
		textFieldNombre.textProperty().bindBidirectional(courant.nombreProperty(), new IntegerStringConverter());

	}

	// Actions

	@FXML
	private void doAnnuler() {
		managerGui.showView(EnumView.MaterielListe);
	}

	@FXML
	private void doValider() {
		modelMateriel.validerMiseAJour();
		managerGui.showView(EnumView.MaterielListe);
	}

	// Méthodes de fonctionnalites

	@FXML
	private void doRechercherUnMateriel() {

	}

	@FXML
	private void doListerTousLesMateriaux() {
		modelMateriel.actualiserListe();
		managerGui.showView(EnumView.MaterielListe);
	}

	@FXML
	private void doAjouterUnmateriel() {
		modelMateriel.preparerAjouter();
		managerGui.showView(EnumView.MaterielForm);
	}

	@FXML
	private void doAttribuer() {

	}

}
