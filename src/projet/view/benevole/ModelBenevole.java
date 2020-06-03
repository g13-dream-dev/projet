 package projet.view.benevole;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoBenevole;
import projet.dao.DaoCoureur;
import projet.dao.DaoPermis;
import projet.data.Benevole;
import projet.data.Coureur;
import projet.data.Permis;
import projet.data.Personne;


public class ModelBenevole {
	
	
	// Données observables 
	
	private final ObservableList<Benevole> liste = FXCollections.observableArrayList();
	
	private final Benevole		courant = new Benevole();
	
	
	// Autres champs
	
    @Inject
	private IMapper		        mapper;
    @Inject
	private DaoBenevole			daoBenevole;
    @Inject
   	private DaoPermis			daoPermis;
	
	
	// Getters 
	
	public ObservableList<Benevole> getListe() {
		return liste;
	}
	
	public Benevole getCourant() {
		return courant;
	}
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoBenevole.listerTout() );
	}

	
	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant, new Benevole() );
	}
	
	public void preparerModifier( Benevole item ) {
		mapper.update( courant, daoBenevole.retrouver( item.getId() ) );
	}
	

	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();
		
		//controle de validite sur courant1
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
		
		if( courant.getSexe() == null || courant.getSexe().isEmpty() ) {
			message.append( "\nselectionnez un sexe." );
		}
		
		if( courant.getNaissance() == null ) {
			message.append( "\nLa naissance ne doit pas être vide." );
		}
		
		if( courant.getTelephone() == null || courant.getTelephone().isEmpty() ) {
			message.append( "\nLe telephone ne doit pas être vide." );
		} else if ( courant.getTelephone().length()> 13 ) {
			message.append( "\nLe telephone est trop long." );
		}
		
		if( courant.getAdresse() == null || courant.getAdresse().isEmpty() ) {
			message.append( "\nL'adresse ne doit pas être vide." );
		} else if ( courant.getAdresse().length()> 100 ) {
			message.append( "\nL'adresse est trop long." );
		}
		
		if( courant.getCodePostal() == null) {
			message.append( "\nle code postal ne doit pas être vide." );
		} else if ( courant.getCodePostal() < 0 ) {
			message.append( "\nle code postal ne doit pas etre negatif." );
		}
		
		if( courant.getEmail() == null || courant.getEmail().isEmpty() ) {
			message.append( "\nL'adresse mail ne doit pas être vide." );
		} else if ( courant.getEmail().length()> 100 ) {
			message.append( "\nL'adresse mail est trop long." );
		}
		
		if(courant.getPermis() != null)
		if( courant.getPermis().getNumero() == null || courant.getPermis().getNumero().isEmpty() ) {
			message.append( "\nLe numero de permis ne doit pas etre vide." );
		} else if ( courant.getPermis().getNumero().length()> 15 ) {
			message.append( "\nLe numero de permis est trop long." );
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}

		
		// Effectue la mise à jour
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoBenevole.inserer( courant ) );
		} else {
			// modficiation
			daoBenevole.modifier( courant );
		}
	}
	

	public void supprimer( Benevole item) {
		daoBenevole.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}
	
}
