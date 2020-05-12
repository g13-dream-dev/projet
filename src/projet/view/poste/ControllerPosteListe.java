package projet.view.poste;

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


public class ControllerPosteListe {
	
	
	// Composants de la vue

	@FXML
	private ListView<Coureur>	listView;
	@FXML
	private Button				buttonAjouter;
	@FXML
	private Button				buttonImprimer;


	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelCoureur		modelCoureur;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {
		
		listView.setCellFactory( UtilFX.cellFactory( item -> item.getClub() ) );
		modelCoureur.actualiserListeClubs();
		// Data binding
		listView.setItems( modelCoureur.getListe() );
		
		// Configuraiton des boutons
		listView.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldVal, newVal) -> {
					configurerBoutons();
		});
		configurerBoutons();
		
		
	}
	
	public void refresh() {
	modelCoureur.actualiserListeClubs();
	UtilFX.selectInListView( listView, modelCoureur.getCourant2() );
	listView.requestFocus();
	}

	
	// Actions
	
	@FXML
	private void doAjouter() {
		modelCoureur.preparerAjouter();;
		managerGui.showView( EnumView.CoureurInscription );
	}

	//@FXML
	//private void doModifier() {
		//modelCoureur.preparerModifier( listView.getSelectionModel().getSelectedItem() );
		//managerGui.showView( EnumView.CoureurInscription );
	//}

	//@FXML
	//private void doSupprimer() {
		//if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
		//	modelCoureur.supprimer( listView.getSelectionModel().getSelectedItem() );
		//	refresh();
		//}
	//}
	
	
	// Gestion des évènements

	// Clic sur la liste
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if ( listView.getSelectionModel().getSelectedIndex() == -1 ) {
					managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
				} else {
					System.out.println(listView.getSelectionModel().getSelectedItem().getClub());
					modelCoureur.actualiserListeCoureurs(listView.getSelectionModel().getSelectedItem().getClub());
					managerGui.showView( EnumView.CoureurForm );
				}
			}
		}
	}

	
	// Méthodes auxiliaires
	
	private void configurerBoutons() {
		
    	if( listView.getSelectionModel().getSelectedItems().isEmpty() ) {
			buttonAjouter.setDisable(true);
			buttonImprimer.setDisable(true);
		} else {
			buttonAjouter.setDisable(false);
			buttonImprimer.setDisable(false);
		}
	}

}
