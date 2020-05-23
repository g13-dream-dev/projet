package projet.view.coureur;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Coureur;
import projet.view.EnumView;
import projet.view.coureur.ModelCoureur;

public class ControllerCoureurListe {

	// Composants de la vue

	@FXML
	private ListView<Coureur> listView;
	@FXML
	private Button buttonModifier;
	@FXML
	private Button buttonSupprimer;

	// Autres champs

	@Inject
	private IManagerGui managerGui;
	@Inject
	private ModelCoureur modelCoureur;

	// Initialisation du Controller

	@FXML
	private void initialize() {

		listView.setCellFactory(UtilFX.cellFactory(item -> item.toString()));
		// Data binding
		listView.setItems(modelCoureur.getCoureurs());
		// Configuraiton des boutons
		listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			configurerBoutons();
		});
		configurerBoutons();

	}

	public void refresh() {
		UtilFX.selectInListView(listView, modelCoureur.getCourant1());
		listView.requestFocus();
	}

	// Actions

	// @FXML
	// private void doAjouter() {
	// modelCoureur.preparerAjouter();
	// managerGui.showView( EnumView.CoureurInscription );
	// }

	@FXML
	private void doModifier() {
		modelCoureur.preparerModifier(listView.getItems().get(0), listView.getItems().get(1));
		managerGui.showView(EnumView.CoureurForm);
	}

	@FXML
	private void doSupprimer() {
		if (managerGui.showDialogConfirm("Confirmez-vous la suppresion ?")) {
			modelCoureur.supprimer(listView.getItems().get(0), listView.getItems().get(1));
			refresh();
			managerGui.showView(EnumView.CoureurClubListe);
		}
	}

	// Gestion des évènements

	// Clic sur la liste
	@FXML
	private void gererClicSurListe(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if (listView.getSelectionModel().getSelectedIndex() == -1) {
					managerGui.showDialogError("Aucun élément n'est sélectionné dans la liste.");
				} else {
					managerGui.showDialogMessage("Les actions sont à effectuer sur tous les membres du club!\n"
							+ "Veuillez selectionner une action sur le bouton concerné en bas.");
				}
			}
		}
	}

	// Méthodes auxiliaires

	private void configurerBoutons() {

		if (listView.getSelectionModel().getSelectedItems().isEmpty()) {
			buttonModifier.setDisable(true);
			buttonSupprimer.setDisable(true);
		} else {
			buttonModifier.setDisable(false);
			buttonSupprimer.setDisable(false);
		}
	}

	// methodes de fonctionnalités

	@FXML
	private void doRechercherUneEquipe() {

	}

	@FXML
	private void doListerToutesLesEquipes() {
		modelCoureur.actualiserListeClubs();
		managerGui.showView(EnumView.CoureurClubListe);
	}

	@FXML
	private void doAjouterUneEquipe() {
		modelCoureur.preparerAjouter();
		managerGui.showView(EnumView.CoureurForm);
	}

	@FXML
	private void doAffilierUneEquipeAuneCompetition() {

	}

}
