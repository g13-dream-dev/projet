 package projet.view.personne;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoPersonne;
import projet.data.Categorie;
import projet.data.Personne;
import projet.data.Telephone;


public class ModelPersonne {
	
	
	// Données observables 
	
	private final ObservableList<Personne> liste = FXCollections.observableArrayList();
	
	private final Personne		courant = new Personne();
	
	
	// Autres champs
	
    @Inject
	private IMapper		        mapper;
    @Inject
	private DaoPersonne			daoPersonne;
	
	
	// Getters 
	
	public ObservableList<Personne> getListe() {
		return liste;
	}
	
	public Personne getCourant() {
		return courant;
	}
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoPersonne.listerTout() );
	}

	
	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant, new Personne() );
	}
	

	public void preparerModifier( Personne item ) {
		mapper.update( courant, daoPersonne.retrouver( item.getId() ) );
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
			courant.setId( daoPersonne.inserer( courant ) );
		} else {
			// modficiation
			daoPersonne.modifier( courant );
		}
	}
	

	public void supprimer( Personne item ) {
		daoPersonne.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}
	

	public void ajouterTelephone() {
		courant.getTelephones().add( new Telephone() );
	}
	

	public void supprimerTelephone( Telephone telephone )  {
		courant.getTelephones().remove( telephone );
	}
	
}
