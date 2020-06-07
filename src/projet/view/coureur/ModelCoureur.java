 package projet.view.coureur;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import projet.commun.IMapper;
import projet.dao.DaoCoureur;
import projet.data.Coureur;
import projet.view.competition.ModelCompetition;


public class ModelCoureur {
	
	
	// Données observables 
	
	private final ObservableList<String> listeClubs = FXCollections.observableArrayList();
	private final ObservableList<Coureur> listeCoureurs = FXCollections.observableArrayList();
	private final ObservableList<Coureur> listetrie = FXCollections.observableArrayList(); 
	
	private final Coureur		courant1 = new Coureur();
	private final Coureur		courant2 = new Coureur();
	
	
	// Autres champs
	
    @Inject
	private IMapper		        mapper;
    @Inject
	private DaoCoureur			daoCoureur;
    @Inject
   	private ModelCompetition			listeCompetition;
    
	
	
	// Getters 
	
	public ObservableList<String> getClubs() {
		return listeClubs;
	}
	
	public ObservableList<Coureur> getCoureurs() {
		return listeCoureurs;
	}
	
	public Coureur getCourant1() {
		return courant1;
	}
	public Coureur getCourant2() {
		return courant2;
	}
	
	// Actualisations
	
	public void actualiserListeClubs() {
		listeClubs.clear();
		List<Coureur> lc = new ArrayList<>();
		lc = daoCoureur.listerTout();
		for(int i = 0; i < lc.size(); i++) {
			if(!listeClubs.contains(lc.get(i).getClub()))listeClubs.add(lc.get(i).getClub());
		}
	}
	public void actualiserListeCoureurs(String club) {
		listeCoureurs.setAll( daoCoureur.listerPourClub(club) );
		if(listeCoureurs.get(0).getPoste().toLowerCase().equals("capitaine")) {
			mapper.update(courant1, listeCoureurs.get(0));
			mapper.update(courant2, listeCoureurs.get(1));
		}else {
			mapper.update(courant1, listeCoureurs.get(1));
			mapper.update(courant2, listeCoureurs.get(0));
		}
		
	}

	
	// Actions
	
	public void preparerAjouter() {
		mapper.update( courant1, new Coureur() );
		mapper.update( courant2, new Coureur() );
	}
	

	public void preparerModifier( Coureur item1 , Coureur item2) {
		mapper.update( courant1, daoCoureur.retrouver( item1.getId() ) );
		mapper.update( courant2, daoCoureur.retrouver( item2.getId() ) );
	}
	

	public void validerMiseAJour() {

		// Vérifie la validité des données
		
		StringBuilder message = new StringBuilder();
		
		//controle de validite sur courant1
		if( courant1.getNom() == null || courant1.getNom().isEmpty() ) {
			message.append( "\nErreur pour Capitaine : Le nom ne doit pas être vide." );
		} else  if ( courant1.getNom().length()> 25 ) {
			message.append( "\nErreur pour Capitaine : Le nom est trop long." );
		}

		if( courant1.getPrenom() == null || courant1.getPrenom().isEmpty() ) {
			message.append( "\nErreur pour Capitaine : Le prénom ne doit pas être vide." );
		} else if ( courant1.getPrenom().length()> 25 ) {
			message.append( "\nErreur pour Capitaine : Le prénom est trop long." );
		}
		
		if( courant1.getSexe() == null || courant1.getSexe().isEmpty() ) {
			message.append( "\nErreur pour Capitaine : selectionnez un sexe." );
		}
		
		if( courant1.getNaissance() == null ) {
			message.append( "\nErreur pour Capitaine : La naissance ne doit pas être vide." );
		}
		
		if( courant1.getTelephone() == null || courant1.getTelephone().isEmpty() ) {
			message.append( "\nErreur pour Capitaine : Le telephone ne doit pas être vide." );
		} else if ( courant1.getTelephone().length()> 13 ) {
			message.append( "\nErreur pour Capitaine : Le telephone est trop long." );
		}
		
		if( courant1.getAdresse() == null || courant1.getAdresse().isEmpty() ) {
			message.append( "\nErreur pour Capitaine : L'adresse ne doit pas être vide." );
		} else if ( courant1.getAdresse().length()> 100 ) {
			message.append( "\nErreur pour Capitaine : L'adresse est trop long." );
		}
		
		if( courant1.getCodePostal() == null) {
			message.append( "\nErreur pour Capitaine : le code postal ne doit pas être vide." );
		} else if ( courant1.getCodePostal() < 0 ) {
			message.append( "\nErreur pour Capitaine : le code postal ne doit pas etre negatif." );
		}
		
		if( courant1.getEmail() == null || courant1.getEmail().isEmpty() ) {
			message.append( "\nErreur pour Capitaine : L'adresse mail ne doit pas être vide." );
		} else if ( courant1.getEmail().length()> 100 ) {
			message.append( "\nErreur pour Capitaine : L'adresse mail est trop long." );
		}
		
		//controle de validite sur courant2
		if( courant2.getNom() == null || courant2.getNom().isEmpty() ) {
			message.append( "\nErreur pour Capitaine : Le nom ne doit pas être vide." );
		} else  if ( courant2.getNom().length()> 25 ) {
			message.append( "\nErreur pour Equipier : Le nom est trop long." );
		}

		if( courant2.getPrenom() == null || courant2.getPrenom().isEmpty() ) {
			message.append( "\nErreur pour Equipier : Le prénom ne doit pas être vide." );
		} else if ( courant2.getPrenom().length()> 25 ) {
			message.append( "\nErreur pour Equipier : Le prénom est trop long." );
		}
		
		if( courant2.getSexe() == null || courant2.getSexe().isEmpty() ) {
			message.append( "\nErreur pour Equipier : selectionnez un sexe." );
		}
		
		if( courant2.getNaissance() == null ) {
			message.append( "\nErreur pour Equipier : La naissance ne doit pas être vide." );
		}
		
		if( courant2.getTelephone() == null || courant2.getTelephone().isEmpty() ) {
			message.append( "\nErreur pour Equipier : Le telephone ne doit pas être vide." );
		} else if ( courant2.getTelephone().length()> 13 ) {
			message.append( "\nErreur pour Equipier : Le telephone est trop long." );
		}
		
		if( courant2.getAdresse() == null || courant2.getAdresse().isEmpty() ) {
			message.append( "\nErreur pour Equipier : L'adresse ne doit pas être vide." );
		} else if ( courant2.getAdresse().length()> 100 ) {
			message.append( "\nErreur pour Equipier : L'adresse est trop long." );
		}
		
		if( courant2.getCodePostal() == null) {
			message.append( "\nErreur pour Capitaine : le code postal ne doit pas être vide." );
		} else if ( courant2.getCodePostal() < 0 ) {
			message.append( "\nErreur pour Capitaine : le code postal ne doit pas etre negatif." );
		}
		
		if( courant2.getEmail() == null || courant2.getEmail().isEmpty() ) {
			message.append( "\nErreur pour Equipier : L'adresse mail ne doit pas être vide." );
		} else if ( courant2.getEmail().length()> 100 ) {
			message.append( "\nErreur pour Equipier : L'adresse mail est trop long." );
		}
		
		if(!courant1.getClub().isEmpty() && !courant2.getClub().isEmpty() && !courant1.getClub().equals(courant2.getClub())) {
			message.append("\nErreur Capitaine et Equipier : Les coureurs doivent etres du meme club.");
		}
		
		if(courant1.getCompetition() == null || courant2.getCompetition() == null) {
			message.append("Vous devez selectionner une competition.");
		}

		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}

		
		// Effectue la mise à jour
		courant1.setPoste("Capitaine");
		courant2.setPoste("Equipier");
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
	

	public void supprimer( Coureur item1, Coureur item2 ) {
		daoCoureur.supprimer( item1.getId() );
		daoCoureur.supprimer( item2.getId() );
		listeClubs.removeIf( club ->(club== item1.getClub()));
	}

	public void viderListe() {
		// TODO Auto-generated method stub
		listeClubs.clear();
		
	}

	public void viderListetrie() {
		// TODO Auto-generated method stub
		listetrie.clear();
		
	}
	
	//getters et setters

	public ObservableList<Coureur> getListe() {
		// TODO Auto-generated method stub
		return listeCoureurs;
	}
	public ObservableList<Coureur> getListeTrie(){ 
		return listetrie;
	}
	
}
