package projet.view.materiel;

import javax.inject.Inject;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Materiel;
import projet.view.EnumView;


public class ControllerMaterielRechercher  {
	
	
	// Composants de la vue

	@FXML
	private ListView<Materiel>	listView;
	@FXML
	private ListView<Materiel>	listViewRecherche;
	@FXML
	private Button				buttonModifier;
	@FXML
	private Button				buttonSupprimer;
	@FXML
	private Button				buttonMemos;
	@FXML
	private ImageView 			buttonSearch;
	@FXML
	private TextField 			textFieldMot;

	
	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelMateriel			modelMateriel;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {
		
		// Configuration de l'objet ListView
		
		// Data binding
		modelMateriel.viderListe();
		listView.setItems( modelMateriel.getListe());

		// Affichage
		listView.setCellFactory( UtilFX.cellFactory( item -> item.toString() ) );		

		// Comportement si modificaiton de la séleciton
		listView.getSelectionModel().getSelectedItems().addListener( 
				(ListChangeListener<Materiel>) (c) -> {
					 configurerBoutons();					
		});
		configurerBoutons();
		
		//configuration de l'object listviewRecherche
		// Data binding
		listViewRecherche.setItems( modelMateriel.getListeTrie());
		// Affichage
		listViewRecherche.setCellFactory( UtilFX.cellFactory( item -> item.toString() ) );
		listViewRecherche.setVisible(false);
		textFieldMot.textProperty().bindBidirectional(modelMateriel.motProperty());
	}
	
	public void refresh() {
		
		
			//modelMateriel.actualiserListe(Mot.getText());
			UtilFX.selectInListView(listView, modelMateriel.getCourant() );
			listView.requestFocus();
		

	}

	
	// Actions

	@FXML
	private void doModifier() {
		modelMateriel.preparerModifier( listView.getSelectionModel().getSelectedItem() );
		managerGui.showView( EnumView.MaterielForm );
	}
	

	@FXML
	private void doSupprimer() {
		if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
			modelMateriel.supprimer( listView.getSelectionModel().getSelectedItem() );
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
			if(event.getClickCount() == 1) {
				if(listViewRecherche.getSelectionModel().getSelectedIndex() == -1) {
					managerGui.showDialogError( "Aucun élément n'est sélectionné parmis les suggestions.");
				}else {
					selectionnerMateriel();
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
	
	private void selectionnerMateriel() {
		modelMateriel.viderListe();
		modelMateriel.getListe().add(listViewRecherche.getSelectionModel().getSelectedItem());
		modelMateriel.viderListetrie();
		listViewRecherche.setVisible(false);
		textFieldMot.setText("");
	}
	
	//Méthodes de fonctionnalites
	
	@FXML
	private void doRechercherUnMateriel(){
		//elle est differente des autres methodes 
		//car celle ci fait rellement la recherche 
		//elle est rattaché a l'image view;
		
		if(modelMateriel.actualiserListeRecherche())listViewRecherche.setVisible(true);
		else listViewRecherche.setVisible(false);
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
