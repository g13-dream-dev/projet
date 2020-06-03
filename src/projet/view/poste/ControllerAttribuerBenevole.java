package projet.view.poste;

import javax.inject.Inject;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.dao.DaoPoste;
import projet.data.Poste;
import projet.view.EnumView;


public class ControllerAttribuerBenevole  {
	
	
	// Composants de la vue

	@FXML
	private ListView<Poste>	listView;
	@FXML
	private Button				buttonAjouter;
	@FXML
	private Button				buttonSupprimer;

	
	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelPoste			modelPoste;
	@Inject
	private DaoPoste				daoPoste;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {
		
		// Configuration de l'objet ListView
		
		// Data binding
		listView.setItems( modelPoste.getListe() );

		// Affichage
		listView.setCellFactory( UtilFX.cellFactory( item -> item.toString() ) );		

		// Comportement si modificaiton de la séleciton
		listView.getSelectionModel().getSelectedItems().addListener( 
				(ListChangeListener<Poste>) (c) -> {
					 configurerBoutons();					
		});
		configurerBoutons();
	}
	
	public void refresh() {
		modelPoste.actualiserListe();
		UtilFX.selectInListView(listView, modelPoste.getCourant() );
		listView.requestFocus();
	}

	
	// Actions

	@FXML
	private void doModifier() {
		modelPoste.preparerModifier( listView.getSelectionModel().getSelectedItem() );
		managerGui.showView( EnumView.PosteForm );
	}

	@FXML
	private void doSupprimer() {
		if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
			modelPoste.supprimer( listView.getSelectionModel().getSelectedItem() );
			refresh();
		}
	}
	
	
	// Gestion des évènements

	// Clic sur la liste
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if ( listView.getSelectionModel().getSelectedIndex() == -1 ) {
					managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
				} else {
					doModifier();
				}
			}
		}
	}


	// Méthodes auxiliaires
	
	private void configurerBoutons() {
    	if( listView.getSelectionModel().getSelectedItems().isEmpty() ) {
			buttonSupprimer.setDisable(true);
		} else {
			buttonSupprimer.setDisable(false);
		}
	}
	
	//Méthodes de fonctionnalites
	
	@FXML
	private void doRechercherUnPoste(){
		//modelPoste.actualiserListe();
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
		managerGui.showView(EnumView.PosteAttribuerBenevole);
	}

}
