 package projet.view.coureur;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoCoureur;
import projet.dao.DaoPersonne;
import projet.data.Coureur;
import projet.data.Personne;
import projet.data.Telephone;


public class ModelCoureur {
	
	
	// Données observables 
	
	private final ObservableList<Coureur> liste = FXCollections.observableArrayList();
	
	private final Coureur		courant = new Coureur();
	
	
	// Autres champs
	
    @Inject
	private IMapper		        mapper;
    @Inject
	private DaoCoureur			daoCoureur;
	
	
	// Getters 
	
	public ObservableList<Coureur> getListe() {
		return liste;
	}
	
	public Personne getCourant() {
		return courant;
	}
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoCoureur.listerTout() );
	}

	
	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant, new Coureur() );
	}
	

	public void preparerModifier( Personne item ) {
		mapper.update( courant, daoCoureur.retrouver( item.getId() ) );
	}
	

	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();
		
		if( courant.getNom() == null || courant.getNom().isEmpty() ) {
			message.append( "\nLe nom ne doit pas être vide." );
		} else  if ( courant.getNom().length()> 25 ) {
			message.append( "\nLe nom est trop long." );
		}

		if( courant.getPrenom() == null || courant.getPrenom().isEmpty() ) {
			message.append( "\nLe prénom ne doit pas être vide." );
		} else if ( courant.getPrenom().length()> 25 ) {
			message.append( "\nLe prénom est trop long." );
		}

		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}

		
		// Effectue la mise à jour
		
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoCoureur.inserer( courant ) );
		} else {
			// modficiation
			daoCoureur.modifier( courant );
		}
	}
	

	public void supprimer( Coureur item ) {
		daoCoureur.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}
	

	public void ajouterTelephone() {
		courant.getTelephones().add( new Telephone() );
	}
	

	public void supprimerTelephone( Telephone telephone )  {
		courant.getTelephones().remove( telephone );
	}
	
}
