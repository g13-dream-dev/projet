package projet.view.materiel;

import java.util.List;

import javax.inject.Inject;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.dao.DaoMateriel;
import projet.data.Materiel;
import projet.view.EnumView;

public class ControllerDistribuerMateriel {

	// Composants de la vue

	@FXML
	private ListView<String> listViewClubs;
	@FXML
	private ListView<Materiel> listViewMateriel;
	@FXML
	private Button buttonDistribuer;

	// Autres champs

	@Inject
	private IManagerGui managerGui;
	@Inject
	private ModelMateriel modelMateriel;
	@Inject
	private projet.view.coureur.ModelCoureur modelCoureur;
	@Inject
	private DaoMateriel daoMateriel;

	@FXML
	private void initialize() {
		// Configuration de l'objet ListViewClubs
		// Data binding
		listViewClubs.setItems(modelCoureur.getClubs());
		// Affichage
		listViewClubs.setCellFactory(UtilFX.cellFactory(item -> (item.toString()+"\tN°: "+daoMateriel.nombreMaterielPour(item))));
		// Comportement si modificaiton de la séleciton
		listViewClubs.getSelectionModel().getSelectedItems().addListener((ListChangeListener<String>) (c) -> {
			configurerBoutons();
		});
		// Configuration de l'objet ListViewMateriel
		// Data binding
		listViewMateriel.setItems(modelMateriel.getListe());
		// Affichage
		listViewMateriel.setCellFactory(UtilFX.cellFactory(item -> item.toString()));
		// Comportement si modificaiton de la séleciton
		listViewMateriel.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Materiel>) (c) -> {
			configurerBoutons();
		});
		configurerBoutons();
	}

	public void refresh() {
		modelCoureur.actualiserListeClubs();
		UtilFX.selectInListView(listViewClubs, modelCoureur.getCourant2().getClub());
		listViewClubs.requestFocus();
		modelMateriel.actualiserListe();
		UtilFX.selectInListView(listViewMateriel, modelMateriel.getCourant() );
		listViewMateriel.requestFocus();
	}

	// Actions

	@FXML
	private void doDistribuer() {
		modelCoureur.actualiserListeCoureurs(listViewClubs.getSelectionModel().getSelectedItem());
		modelMateriel.distribuer(listViewMateriel.getSelectionModel().getSelectedItem(), modelCoureur.getCoureurs());
		refresh();
	}

	// Gestion des évènements

	// Clic sur la liste
	@FXML
	private void gererClicSurListe(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if(event.getSource() == listViewClubs) {
					if (listViewClubs.getSelectionModel().getSelectedIndex() == -1 ) {
						managerGui.showDialogError("Aucun élément n'est sélectionné dans la liste.");
					}else {
						modelCoureur.actualiserListeCoureurs(listViewClubs.getSelectionModel().getSelectedItem());
						String message = "Liste du materiel du club "+listViewClubs.getSelectionModel().getSelectedItem().toString();
						List<Materiel> listeMateriel = daoMateriel.listerPourCoureur(modelCoureur.getCoureurs().get(0));
						for(int i = 0; i < listeMateriel.size(); i++)message+="\n\t- "+listeMateriel.get(i).toString();
						managerGui.showDialogMessage(message);
					}
				}
				
			}
		}
	}

	// Méthodes auxiliaires

	private void configurerBoutons() {
		if (listViewClubs.getSelectionModel().getSelectedItems().isEmpty() && listViewMateriel.getSelectionModel().getSelectedItems().isEmpty()) {
			buttonDistribuer.setDisable(true);
		} else {
			buttonDistribuer.setDisable(false);
		}
	}

	// Méthodes de fonctionnalites

	@FXML
	private void doRechercherUnMateriel() {
		// modelMateriel.actualiserListe();
		managerGui.showView(EnumView.MaterielRechercher);

	}

	@FXML
	private void doListerTousLeMateriel() {
		modelMateriel.actualiserListe();
		managerGui.showView(EnumView.MaterielListe);
	}

	@FXML
	private void doAjouterUnmateriel() {
		modelMateriel.preparerAjouter();
		managerGui.showView(EnumView.MaterielForm);
	}

	@FXML
	private void doDistribuerMateriel() {
		managerGui.showView(EnumView.MaterielDistribuer);
	}

}
