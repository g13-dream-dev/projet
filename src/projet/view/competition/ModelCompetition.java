 package projet.view.competition;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoCompetition;
import projet.data.Competition;


public class ModelCompetition {
	
	
	// Données observables 
	
	private final ObservableList<Competition> liste = FXCollections.observableArrayList();
	
	private final Competition		courant = new Competition();
	
	
	
	// Autres champs
	
    @Inject
	private IMapper		        mapper;
    @Inject
	private DaoCompetition		daoCompetition;
    @Inject
    private ModelCourse modelCourse;
    
	
	
	// Getters 
	
	public ObservableList<Competition> getListe() {
		return liste;
	}
	
	public Competition getCourant() {
		return courant;
	}
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll( daoCompetition.listerTout() );
	}

	
	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant, new Competition() );
		modelCourse.actualiserNombreCourses();
	}
	

	public void preparerModifier( Competition item ) {
		mapper.update( courant, daoCompetition.retrouver( item.getId() ) );
		modelCourse.actualiserNombreCourses();
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
		
		if( courant.getLieu() == null ) {
			message.append( "\nLe lieu ne doit pas être vide." );
		} 
		
		if( courant.getDate() == null ) {
			message.append( "\nLa date ne doit pas être vide." );
		}
		
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}

		// Effectue la mise à jour
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoCompetition.inserer( courant ) );
		} else {
			// modficiation
			daoCompetition.modifier( courant );
		}
	}
	

	public void supprimer( Competition item) {
		daoCompetition.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}
	
}
