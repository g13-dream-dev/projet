package projet.view.personne;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.view.IManagerGui;
import projet.data.Personne;
import projet.view.EnumView;


public class ControllerPersonneForm  {
	
	
	// Composants de la vue
	
	@FXML
	private TextField			textFieldId;
	@FXML
	private TextField			textFieldNom;
	@FXML	
	private TextField			textFieldPrenom;

	
	// Autres champs
	@Inject
	private IManagerGui			managerGui;
	@Inject
	private ModelPersonne		modelPersonne;
    
	
	// Initialisation du controller
	
	public void initialize() {

		Personne courant = modelPersonne.getCourant();
		
		// Champs simples
		textFieldId.textProperty().bindBidirectional( courant.idProperty(), new ConverterStringInteger() );
		textFieldNom.textProperty().bindBidirectional( courant.nomProperty() );
		textFieldPrenom.textProperty().bindBidirectional( courant.prenomProperty() );

        
		// Configuration de la combo box

		// Data binding
		//comboBoxCategorie.setItems(  modelPersonne.getCategories());
        //comboBoxCategorie.valueProperty().bindBidirectional( courant.categorieProperty() );
 			
	
	}
	
	
	// Actions
	
	@FXML
	private void doValider() {
		modelPersonne.validerMiseAJour();
		managerGui.showView( EnumView.PersonneListe );
	}
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.PersonneListe );
	}
	
	/*
	 * @FXML private void doAjouterTelephone() { modelPersonne.ajouterTelephone(); }
	 * 
	 * 
	 * @FXML private void doiSupprimerTelephone() { Telephone telephone =
	 * tableViewTelphones.getSelectionModel().getSelectedItem();
	 * modelPersonne.supprimerTelephone(telephone); }
	 */
    
}
