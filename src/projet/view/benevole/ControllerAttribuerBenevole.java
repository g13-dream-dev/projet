package projet.view.benevole;

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
import projet.dao.DaoPoste;
import projet.data.Benevole;
import projet.data.Poste;
import projet.view.EnumView;
import projet.view.benevole.ModelBenevole;
import projet.view.poste.ModelPoste;

public class ControllerAttribuerBenevole {

	// Composants de la vue

	@FXML
	private ListView<Benevole> listViewBenevole;
	@FXML
	private ListView<Poste> listViewPoste;
	@FXML
	private Button buttonAffecter;

	// Autres champs

	@Inject
	private IManagerGui managerGui;
	@Inject
	private ModelPoste modelPoste;
	@Inject
	private ModelBenevole modelBenevole;
	@Inject
	private DaoPoste daoPoste;

	@FXML
	private void initialize() {

		// Configuration de l'objet ListViewBenevole
		// Data binding
		listViewBenevole.setItems(modelBenevole.getListe());
		// Affichage
		listViewBenevole.setCellFactory(UtilFX.cellFactory(item -> (item.toString()+"\tN°: "+daoPoste.nombrePostePour(item))));
		// Comportement si modificaiton de la séleciton
		listViewBenevole.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Benevole>) (c) -> {
			configurerBoutons();
		});
		// Configuration de l'objet ListViewPoste
		// Data binding
		listViewPoste.setItems(modelPoste.getListe());
		// Affichage
		listViewPoste.setCellFactory(UtilFX.cellFactory(item -> item.toString()));
		// Comportement si modificaiton de la séleciton
		listViewPoste.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Poste>) (c) -> {
			configurerBoutons();
		});
		configurerBoutons();
	}

	public void refresh() {
		modelBenevole.actualiserListe();
		UtilFX.selectInListView(listViewBenevole, modelBenevole.getCourant() );
		listViewBenevole.requestFocus();
		modelPoste.actualiserListe();
		UtilFX.selectInListView(listViewPoste, modelPoste.getCourant() );
		listViewPoste.requestFocus();
	}

	// Actions

	@FXML
	private void doAffecter() {
		modelPoste.affecter(listViewPoste.getSelectionModel().getSelectedItem(), listViewBenevole.getSelectionModel().getSelectedItem());
		refresh();
	}

	// Gestion des évènements

	// Clic sur la liste
	@FXML
	private void gererClicSurListe(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if(event.getSource() == listViewBenevole) {
					if (listViewBenevole.getSelectionModel().getSelectedIndex() == -1 ) {
						managerGui.showDialogError("Aucun élément n'est sélectionné dans la liste.");
					}else {
						String message = "Liste des postes de M./Mme "+listViewBenevole.getSelectionModel().getSelectedItem().toString();
						List<Poste> listePoste = daoPoste.listerPourBenevole(listViewBenevole.getSelectionModel().getSelectedItem());
						for(int i = 0; i < listePoste.size(); i++)message+="\n\t- "+listePoste.get(i).toString();
						managerGui.showDialogMessage(message);
					}
				}
				
			}
		}
	}

	// Méthodes auxiliaires

	private void configurerBoutons() {
		if (listViewBenevole.getSelectionModel().getSelectedItems().isEmpty() && listViewPoste.getSelectionModel().getSelectedItems().isEmpty()) {
			buttonAffecter.setDisable(true);
		} else {
			buttonAffecter.setDisable(false);
		}
	}

	// Méthodes de fonctionnalites

	@FXML
	private void doListerTousLesBenevoles() {
		modelBenevole.actualiserListe();
		managerGui.showView(EnumView.BenevoleListe);
	}
	
	@FXML
	private void doAjouterUnBenevole() {
		//modelBenevole.preparerAjouter();
		managerGui.showView(EnumView.BenevoleForm);
	}

	@FXML
	private void doAffecterDesPostesAuxBenevoles() {
		managerGui.showView(EnumView.BenevoleAttribuerPoste);
	}

}
