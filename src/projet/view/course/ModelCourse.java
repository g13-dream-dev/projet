 package projet.view.course;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoCourse;
import projet.dao.DaoCoureur;
import projet.dao.DaoPermis;
import projet.data.Course;
import projet.data.Coureur;
import projet.data.Permis;
import projet.data.Personne;


public class ModelCourse {
	
	
	// Données observables 
	
	private final ObservableList<Course> liste = FXCollections.observableArrayList();
	
	private final Course		courant = new Course();
	
	
	
	// Autres champs
	
    @Inject
	private IMapper		        mapper;
    @Inject
	private DaoCourse			daoCourse;
    
	
	
	// Getters 
	
	public ObservableList<Course> getListe() {
		return liste;
	}
	
	public Course getCourant() {
		return courant;
	}
	
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoCourse.listerTout() );
	}

	
	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant, new Course() );
		
	}
	

	public void preparerModifier( Course item ) {
		mapper.update( courant, daoCourse.retrouver( item.getId() ) );
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
		
		if( courant.getHeureD() == null || courant.getHeureD().isEmpty() ) {
			message.append( "\nL'heure de depart ne doit pas être vide." );
		} 
		
		
		if( courant.getLieuDepart() == null || courant.getLieuDepart().isEmpty() ) {
			message.append( "\nLe lieu de depart ne doit pas être vide." );
		} else  if ( courant.getLieuDepart().length()> 25 ) {
			message.append( "\nLe lieu de depart est trop long." );
		}
		
		
		
		if( courant.getLieuArriv() == null || courant.getLieuArriv().isEmpty() ) {
			message.append( "\nLe lieu d'arrivée ne doit pas être vide." );
		} else  if ( courant.getLieuArriv().length()> 25 ) {
			message.append( "\nLe lieu d'arrivée est trop long." );
		}

		
		if( courant.getDistance() == null) {
			message.append( "\nla distance ne doit pas être vide." );
		} else if ( courant.getDistance()< 0 ) {
			message.append( "\nla distance ne doit pas etre negatif." );
		}
		
		
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}

		
		// Effectue la mise à jour
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoCourse.inserer( courant ) );
		} else {T
			// modficiation
			daoCourse.modifier( courant );
		}
	}
	

	public void supprimer( Course item) {
		daoCourse.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}
	
}
