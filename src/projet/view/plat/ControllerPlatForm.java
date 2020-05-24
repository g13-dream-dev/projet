package projet.view.plat;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.view.IManagerGui;
import projet.data.Plat;
import projet.view.EnumView;

public class ControllerPlatForm {

	// Composants de la vue

	@FXML
	private TextField textFieldId;
	@FXML
	private TextField textFieldNom;
	@FXML
	private TextField textFieldNombre;

	// Autres champs

	private Plat courant;

	@Inject
	private IManagerGui managerGui;
	@Inject
	private ModelPlat modelPlat;

	// Initialisation du Controller

	@FXML
	private void initialize() {

		courant = modelPlat.getCourant();

		// Data binding
		courant = modelPlat.getCourant();
		textFieldId.textProperty().bindBidirectional(courant.idProperty(), new ConverterStringInteger());
		textFieldNom.textProperty().bindBidirectional(courant.nomProperty());
		textFieldNombre.textProperty().bindBidirectional(courant.nombreProperty(), new IntegerStringConverter());

	}

	// Actions

	@FXML
	private void doAnnuler() {
		managerGui.showView(EnumView.PlatListe);
	}

	@FXML
	private void doValider() {
		modelPlat.validerMiseAJour();
		managerGui.showView(EnumView.PlatListe);
	}

	// Méthodes de fonctionnalites

	@FXML
	private void doRechercherUnPlat() {

	}

	@FXML
	private void doListerTousLesPlats() {
		modelPlat.actualiserListe();
		managerGui.showView(EnumView.PlatListe);
	}

	@FXML
	private void doAjouterUnplat() {
		modelPlat.preparerAjouter();
		managerGui.showView(EnumView.PlatForm);
	}

	@FXML
	private void doReserverDesPLatsSupplementaire() {

	}

}
