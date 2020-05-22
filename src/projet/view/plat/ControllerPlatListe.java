package projet.view.plat;

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
import projet.dao.DaoPlat;
import projet.data.Plat;
import projet.view.EnumView;


public class ControllerPlatListe  {
	
	
	// Composants de la vue

	@FXML
	private ListView<Plat>	listView;
	@FXML
	private Button				buttonModifier;
	@FXML
	private Button				buttonSupprimer;
	@FXML
	private Button				buttonMemos;

	
	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelPlat			modelPlat;
	@Inject
	private DaoPlat				daoPlat;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {
		
		// Configuration de l'objet ListView
		
		// Data binding
		listView.setItems( modelPlat.getListe() );

		// Affichage
		listView.setCellFactory( UtilFX.cellFactory( item -> item.toString() ) );		

		// Comportement si modificaiton de la séleciton
		listView.getSelectionModel().getSelectedItems().addListener( 
				(ListChangeListener<Plat>) (c) -> {
					 configurerBoutons();					
		});
		configurerBoutons();
	}
	
	public void refresh() {
		modelPlat.actualiserListe();
		UtilFX.selectInListView(listView, modelPlat.getCourant() );
		listView.requestFocus();
	}

	
	// Actions

	@FXML
	private void doAjouter() {
		modelPlat.preparerAjouter();
		managerGui.showView( EnumView.PlatForm );
	}

	@FXML
	private void doModifier() {
		modelPlat.preparerModifier( listView.getSelectionModel().getSelectedItem() );
		managerGui.showView( EnumView.PlatForm );
	}

	@FXML
	private void doSupprimer() {
		if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
			modelPlat.supprimer( listView.getSelectionModel().getSelectedItem() );
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
			buttonModifier.setDisable(true);
			buttonSupprimer.setDisable(true);
		} else {
			buttonModifier.setDisable(false);
			buttonSupprimer.setDisable(false);
		}
	}

}
