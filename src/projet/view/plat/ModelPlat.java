package projet.view.plat;


import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoPlat;
import projet.data.Plat;


public class ModelPlat {
	
	
	// Données observables 
	
	private final ObservableList<Plat> liste = FXCollections.observableArrayList(); 
	
	private final Plat	courant = new Plat();
	
	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
	private DaoPlat		daoPlat;
	
	
	// Getters
	
	public ObservableList<Plat> getListe() {
		return liste;
	}

	public Plat getCourant() {
		return courant;
	}
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoPlat.listerTout() );
 	}
	
	
	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant, new Plat() );
	}

	
	public void preparerModifier( Plat item ) {
		mapper.update( courant, daoPlat.retrouver( item.getId() ) );
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
			courant.setId( daoPlat.inserer( courant ) );
		} else {
			// modficiation
			daoPlat.modifier( courant );
		}
	}
	
	
	public void supprimer( Plat item ) {
		daoPlat.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}

}
