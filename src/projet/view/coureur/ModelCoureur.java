 package projet.view.coureur;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoCoureur;
import projet.data.Coureur;
import projet.data.Personne;
import projet.data.Telephone;


public class ModelCoureur {
	
	
	// Données observables 
	
	private final ObservableList<Coureur> liste = FXCollections.observableArrayList();
	
	private final Coureur		courant1 = new Coureur();
	private final Coureur		courant2 = new Coureur();
	
	
	// Autres champs
	
    @Inject
	private IMapper		        mapper;
    @Inject
	private DaoCoureur			daoCoureur;
	
	
	// Getters 
	
	public ObservableList<Coureur> getListe() {
		return liste;
	}
	
	public Coureur getCourant1() {
		return courant1;
	}
	public Coureur getCourant2() {
		return courant2;
	}
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoCoureur.listerTout() );
	}

	
	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant1, new Coureur() );
		mapper.update( courant2, new Coureur() );
	}
	

	public void preparerModifier( Personne item ) {
		mapper.update( courant1, daoCoureur.retrouver( item.getId() ) );
		mapper.update( courant2, daoCoureur.retrouver( item.getId() ) );
	}
	

	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();
		
		if( courant1.getNom() == null || courant1.getNom().isEmpty() ) {
			message.append( "\nLe nom ne doit pas être vide." );
		} else  if ( courant1.getNom().length()> 25 ) {
			message.append( "\nLe nom est trop long." );
		}

		if( courant1.getPrenom() == null || courant1.getPrenom().isEmpty() ) {
			message.append( "\nLe prénom ne doit pas être vide." );
		} else if ( courant1.getPrenom().length()> 25 ) {
			message.append( "\nLe prénom est trop long." );
		}
		
		if( courant2.getNom() == null || courant2.getNom().isEmpty() ) {
			message.append( "\nLe nom ne doit pas être vide." );
		} else  if ( courant2.getNom().length()> 25 ) {
			message.append( "\nLe nom est trop long." );
		}

		if( courant2.getPrenom() == null || courant2.getPrenom().isEmpty() ) {
			message.append( "\nLe prénom ne doit pas être vide." );
		} else if ( courant2.getPrenom().length()> 25 ) {
			message.append( "\nLe prénom est trop long." );
		}

		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}

		
		// Effectue la mise à jour
		
		if ( courant1.getId() == null ) {
			// Insertion
			courant1.setId( daoCoureur.inserer( courant1 ) );
		} else {
			// modficiation
			daoCoureur.modifier( courant1 );
		}
		if ( courant2.getId() == null ) {
			// Insertion
			courant2.setId( daoCoureur.inserer( courant2 ) );
		} else {
			// modficiation
			daoCoureur.modifier( courant2 );
		}
	}
	

	public void supprimer( Coureur item ) {
		daoCoureur.supprimer( item.getId() );
		mapper.update( courant1, UtilFX.findNext( liste, item ) );
		mapper.update( courant2, UtilFX.findNext( liste, item ) );
	}
	

	public void ajouterTelephone() {
		courant1.getTelephones().add( new Telephone() );
		courant2.getTelephones().add( new Telephone() );
	}
	

	public void supprimerTelephone( Telephone telephone )  {
		courant1.getTelephones().remove( telephone );
		courant2.getTelephones().remove( telephone );
	}
	
}
