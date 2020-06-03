package projet.view.poste;

import javax.inject.Inject;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.dao.DaoPoste;
import projet.data.Poste;
import projet.view.EnumView;


public class ControllerPosteRechercher  {
	
	
	// Composants de la vue

	@FXML
	private ListView<Poste>	listView;
	@FXML
	private ListView<Poste>	listViewRecherche;
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
	private ModelPoste			modelPoste;
	@Inject
	private DaoPoste				daoPoste;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {
		
		// Configuration de l'objet ListView
		
		// Data binding
		modelPoste.viderListe();
		listView.setItems( modelPoste.getListe());

		// Affichage
		listView.setCellFactory( UtilFX.cellFactory( item -> item.toString() ) );		

		// Comportement si modificaiton de la séleciton
		listView.getSelectionModel().getSelectedItems().addListener( 
				(ListChangeListener<Poste>) (c) -> {
					 configurerBoutons();					
		});
		configurerBoutons();
		
		//configuration de l'object listviewRecherche
		// Data binding
		listViewRecherche.setItems( modelPoste.getListeTrie());
		// Affichage
		listViewRecherche.setCellFactory( UtilFX.cellFactory( item -> item.toString() ) );
		listViewRecherche.setVisible(false);
		textFieldMot.textProperty().bindBidirectional(modelPoste.motProperty());
	}
	
	public void refresh() {
		
		
			//modelPoste.actualiserListe(Mot.getText());
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
			if(event.getClickCount() == 1) {
				if(listViewRecherche.getSelectionModel().getSelectedIndex() == -1) {
					managerGui.showDialogError( "Aucun élément n'est sélectionné parmis les suggestions.");
				}else {
					selectionnerPoste();
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
	
	private void selectionnerPoste() {
		modelPoste.viderListe();
		modelPoste.getListe().add(listViewRecherche.getSelectionModel().getSelectedItem());
		modelPoste.viderListetrie();
		listViewRecherche.setVisible(false);
		textFieldMot.setText("");
	}
	
	//Méthodes de fonctionnalites
	
	@FXML
	private void doRechercherUnPoste(){
		//elle est differente des autres methodes 
		//car celle ci fait rellement la recherche 
		//elle est rattaché a l'image view;
		
		//daoPoste.listerRecherche(Mot.toString());
		//modelPoste.actualiserListe();
		if(modelPoste.actualiserListeRecherche())listViewRecherche.setVisible(true);
		else listViewRecherche.setVisible(false);
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
		
	}

}
