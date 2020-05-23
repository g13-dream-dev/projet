package projet.view.competition;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Competition;
import projet.view.EnumView;


public class ControllerCompetitionListe {
	
	
	// Composants de la vue

	@FXML
	private ListView<Competition>		listView;
	@FXML
	private Button				buttonModifier;
	@FXML
	private Button				buttonSupprimer;


	// Autres champs
	
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelCompetition		modelCompetition;
	
	
	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		listView.setItems( modelCompetition.getListe() );
		
		listView.setCellFactory(  UtilFX.cellFactory( item -> item.getNom() ));
		
		// Configuraiton des boutons
		listView.getSelectionModel().selectedItemProperty().addListener(
				(obs, oldVal, newVal) -> {
					configurerBoutons();
		});
		configurerBoutons();

	}
	
	public void refresh() {
		modelCompetition.actualiserListe();
		UtilFX.selectInListView( listView, modelCompetition.getCourant() );
		listView.requestFocus();
	}

	
	// Actions
	
	@FXML
	private void doAjouter() {
		modelCompetition.preparerAjouter();
		managerGui.showView( EnumView.CompetitionForm );

	}

	@FXML
	private void doModifier() {
		Competition item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			modelCompetition.preparerModifier(item);
			managerGui.showView(EnumView.CompetitionForm );
		}
	}

	@FXML
	private void doSupprimer() {
		Competition item = listView.getSelectionModel().getSelectedItem();
		if ( item == null ) {
			managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
		} else {
			boolean reponse = managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" );
			if ( reponse ) {
				modelCompetition.supprimer( item );
				refresh();
			}
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
	
	//methodes de fonctionnalités
	@FXML
	private void doListerToutesLesCompetitions() {
		modelCompetition.actualiserListe();
		managerGui.showView(EnumView.CompetitionListe);
	}
	
	@FXML
	private void doAjouterUneCompetition() {
		modelCompetition.preparerAjouter();
		managerGui.showView(EnumView.CompetitionForm);
	}

}
