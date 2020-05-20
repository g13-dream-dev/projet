package projet.data;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Coureur extends Personne {

	private final StringProperty		club	 	= new SimpleStringProperty();
	private final StringProperty		poste		= new SimpleStringProperty();
	public Coureur() {
	}

	public Coureur(int id, String nom, String prenom, String sexe, LocalDate naissance,String telephone, String adresse, int codePostal,
			String email, String club, String poste) {
		super(id, nom, prenom, sexe, naissance, telephone, adresse, codePostal, email);
		setClub(club);
		setPoste(poste);
	}

	public final StringProperty clubProperty() {
		return this.club;
	}
	

	public final String getClub() {
		return this.clubProperty().get();
	}
	

	public final void setClub(final String club) {
		this.clubProperty().set(club);
	}
	

	public final StringProperty posteProperty() {
		return this.poste;
	}
	

	public final String getPoste() {
		return this.posteProperty().get();
	}
	

	public final void setPoste(final String poste) {
		this.posteProperty().set(poste);
	}
	@Override
	public String toString() {
		
		return getPoste().toUpperCase()+": "+super.toString();
	}
	

}
