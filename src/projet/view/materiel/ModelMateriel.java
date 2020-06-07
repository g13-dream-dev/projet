package projet.view.materiel;

import java.util.List;

import javax.inject.Inject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionValidation;
import jfox.javafx.util.UtilFX;
import projet.commun.IMapper;
import projet.dao.DaoMateriel;
import projet.data.Benevole;
import projet.data.Coureur;
import projet.data.Materiel;
import projet.data.Poste;

public class ModelMateriel {

	// Données observables

	private final ObservableList<Materiel> liste = FXCollections.observableArrayList();
	private final ObservableList<Materiel> listetrie = FXCollections.observableArrayList();

	private final Materiel courant = new Materiel();
	private final StringProperty mot = new SimpleStringProperty();

	// Autres champs
	@Inject
	private IMapper mapper;
	@Inject
	private DaoMateriel daoMateriel;

	// Getters

	public ObservableList<Materiel> getListe() {
		return liste;
	}

	public void viderListe() {
		liste.clear();
	}

	public ObservableList<Materiel> getListeTrie() {
		return listetrie;
	}

	public void viderListetrie() {
		listetrie.clear();
	}

	public Materiel getCourant() {
		return courant;
	}

	public StringProperty motProperty() {
		return mot;
	}

	// Actualisations

	public void actualiserListe() {
		liste.setAll(daoMateriel.listerTout());
	}

	public boolean actualiserListeRecherche() {
		// listetrie contient les elements de la requette sql
		if (mot != null && !mot.getValue().isEmpty())
			listetrie.setAll(daoMateriel.listerRecherche(mot.getValue()));
		if (listetrie.size() > 0)
			return true;
		return false;
	}

	// Actions

	public void preparerAjouter() {
		mapper.update(courant, new Materiel());
	}

	public void preparerModifier(Materiel item) {
		mapper.update(courant, daoMateriel.retrouver(item.getId()));
	}

	public void validerMiseAJour() {

		// Vérifie la validité des données

		StringBuilder message = new StringBuilder();

		if (courant.getNom() == null || courant.getNom().isEmpty()) {
			message.append("\nLe nom ne doit pas être vide.");
		} else if (courant.getNom().length() < 3) {
			message.append("\nLe nom est trop court : 3 mini.");
		}
		if (courant.getNombre() < 0) {
			message.append("\nLe nombre ne doit pas etre inferieur a 0.");
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}

		// Effectue la mise à jour

		if (courant.getId() == null) {
			// Insertion
			courant.setId(daoMateriel.inserer(courant));
		} else {
			// modficiation
			daoMateriel.modifier(courant);
		}
	}
	
	public void distribuer(Materiel materiel, List<Coureur> coureur) {
		// Vérifie la validité des données
		StringBuilder message = new StringBuilder();
		
		if( materiel.getNombreDistribue() == materiel.getNombre()) {
			message.append( "\nle stock de ce materiel est epuisé." );
		}
		if ( daoMateriel.estDejaDistribue(materiel, coureur.get(0)) || daoMateriel.estDejaDistribue(materiel, coureur.get(1))) {
			message.append( "\nLe materiel a deja ete distribue a ce club." );
		}
		
		if ( message.length() > 0 ) {
			throw new ExceptionValidation( message.toString().substring(1) );
		}else {
			daoMateriel.DistribuerMaterielAuCoureur(materiel, coureur.get(0));
			daoMateriel.DistribuerMaterielAuCoureur(materiel, coureur.get(1));
		}
		
	}

	public void supprimer(Materiel item) {
		daoMateriel.supprimer(item.getId());
		mapper.update(courant, UtilFX.findNext(liste, item));
	}

}
