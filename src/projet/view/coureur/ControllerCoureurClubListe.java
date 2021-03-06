package projet.view.coureur;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.view.EnumView;
import projet.view.coureur.ModelCoureur;

public class ControllerCoureurClubListe {

	// Composants de la vue

	@FXML
	private ListView<String> listView;
	@FXML
	private Button buttonModifier;

	// Autres champs

	@Inject
	private IManagerGui managerGui;
	@Inject
	private ModelCoureur modelCoureur;

	// Initialisation du Controller

	@FXML
	private void initialize() {

		listView.setCellFactory(UtilFX.cellFactory(item -> item));
		modelCoureur.actualiserListeClubs();
		// Data binding
		listView.setItems(modelCoureur.getClubs());

		// Configuraiton des boutons
		listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			configurerBoutons();
		});
		configurerBoutons();
	}

	public void refresh() {
		modelCoureur.actualiserListeClubs();
		UtilFX.selectInListView(listView, modelCoureur.getCourant2().getClub());
		listView.requestFocus();
	}

	// Actions

	@FXML
	private void doAjouter() {
		modelCoureur.preparerAjouter();
		managerGui.showView(EnumView.CoureurForm);
	}

	@FXML
	private void doConsulter() {
		modelCoureur.actualiserListeCoureurs(listView.getSelectionModel().getSelectedItem());
		modelCoureur.preparerModifier(modelCoureur.getCourant1(), modelCoureur.getCourant2());
		managerGui.showView(EnumView.CoureurListe);
	}

	// @FXML
	// private void doSupprimer() {
	// if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
	// modelCoureur.supprimer( listView.getSelectionModel().getSelectedItem() );
	// refresh();
	// }
	// }

	// Gestion des évènements

	// Clic sur la liste
	@FXML
	private void gererClicSurListe(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if (listView.getSelectionModel().getSelectedIndex() == -1) {
					managerGui.showDialogError("Aucun élément n'est sélectionné dans la liste.");
				} else {
					doConsulter();
				}
			}
		}
	}

	// Méthodes auxiliaires

	private void configurerBoutons() {

		if (listView.getSelectionModel().getSelectedItems().isEmpty()) {
			buttonModifier.setDisable(true);
		} else {
			buttonModifier.setDisable(false);
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
