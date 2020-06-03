package projet.view.materiel;


import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoMateriel;
import projet.data.Materiel;


public class ModelMateriel {
	
	
	// Données observables 
	
	private final ObservableList<Materiel> liste = FXCollections.observableArrayList(); 
	
	private final Materiel	courant = new Materiel();
	
	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoMateriel		daoMateriel;
	
	
	// Getters
	
	public ObservableList<Materiel> getListe() {
		return liste;
	}

	public Materiel getCourant() {
		return courant;
	}
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoMateriel.listerTout() );
 	}
	
	
	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant, new Materiel() );
	}

	
	public void preparerModifier( Materiel item ) {
		mapper.update( courant, daoMateriel.retrouver( item.getId() ) );
	}
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();
		
		if( courant.getNom() == null || courant.getNom().isEmpty() ) {
			message.append( "\nLe nom ne doit pas être vide." );
		} else 	if ( courant.getNom().length() < 3 ) {
			message.append( "\nLe nom est trop court : 3 mini." );
		}
		if ( courant.getNombre() < 0 ) {
			message.append( "\nLe nombre ne doit pas etre inferieur a 0." );
		}
		
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		
		// Effectue la mise à jour
		
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoMateriel.inserer( courant ) );
		} else {
			// modficiation
			daoMateriel.modifier( courant );
		}
	}
	
	
	public void supprimer( Materiel item ) {
		daoMateriel.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}

}
