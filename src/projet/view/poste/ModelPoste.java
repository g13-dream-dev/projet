package projet.view.poste;


import javax.inject.Inject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoPoste;
import projet.data.Benevole;
import projet.data.Poste;
import projet.view.ManagerGui;
import projet.view.benevole.ModelBenevole;


public class ModelPoste {
	
	
	// Données observables 
	
	private final ObservableList<Poste> liste = FXCollections.observableArrayList(); 
	private final ObservableList<Poste> listetrie = FXCollections.observableArrayList();
	

	
	private final Poste	courant = new Poste();
	private final StringProperty mot = new SimpleStringProperty();
	
	
	
	
	// Autres champs
    @Inject
	private IMapper			mapper;
    @Inject
    private ManagerGui managerGui;
    @Inject
	private DaoPoste		daoPoste;
	
	
	// Getters
	
	public ObservableList<Poste> getListe() {
		return liste;
	}
	public void viderListe() {
		liste.clear();
	}
	
	public ObservableList<Poste> getListeTrie(){ 
		return listetrie;
	}
	public void viderListetrie() {
		listetrie.clear();
	}

	public Poste getCourant() {
		return courant;
	}
	
	public StringProperty motProperty() {
		return mot;
	}
	
	// Actualisations
	
	public void actualiserListe() {
		liste.setAll(daoPoste.listerTout());
		
	}
	
	public boolean actualiserListeRecherche() {
		//listetrie contient les elements de la requette sql
		if(mot != null && !mot.getValue().isEmpty())
		listetrie.setAll(daoPoste.listerRecherche(mot.getValue()));
		if(listetrie.size()>0)return true;
		return false;
	}
	
	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant, new Poste() );
	}

	
	public void preparerModifier( Poste item ) {
		mapper.update( courant, daoPoste.retrouver( item.getId() ) );
	}
	
	
	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();
		
		if( courant.getLibelle() == null || courant.getLibelle().isEmpty() ) {
			message.append( "\nLe libelle ne doit pas être vide." );
		} else 	if ( courant.getLibelle().length() < 3 ) {
			message.append( "\nLe libelle est trop court : 3 mini." );
		}
		if( courant.getEtat() == null || courant.getEtat().isEmpty() ) {
			message.append( "\nVous devez selectionner un état." );
		}
		if( courant.getHeureD() == null ) {
			message.append( "\nL'heure de depard ne doit pas être vide." );
		}
		if( courant.getNombrePlaces() == null) {
			message.append( "\nLe libelle ne doit pas être vide." );
		} else 	if ( courant.getLibelle().length() < 0 ) {
			message.append( "\nLe nombre de places ne doit pas être inferieur à 0." );
			
		}
		
		
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}
		
		
		// Effectue la mise à jour
		
		if ( courant.getId() == null ) {
			// Insertion
			courant.setId( daoPoste.inserer( courant ) );
		} else {
			// modficiation
			daoPoste.modifier( courant );
		}
	}
	
	public void affecter(Poste poste, Benevole benevole) {
		// Vérifie la validité des données
		StringBuilder message = new StringBuilder();
		
		if( poste.getPlacesOccupees() == poste.getNombrePlaces()) {
			message.append( "\nil n'y a plus de places libres pour ce post" );
		}
		if ( daoPoste.estDejaAttribue(poste, benevole)) {
			message.append( "\nLe bénévole a déjà été affecté à ce poste." );
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}else {
			daoPoste.attribuerBenevoleAuPoste(poste, benevole);
		}
		
	}
	
	
	public void supprimer( Poste item ) {
		daoPoste.supprimer( item.getId() );
		mapper.update( courant, UtilFX.findNext( liste, item ) );
	}


}
