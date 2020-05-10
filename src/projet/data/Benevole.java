package projet.data;

import java.time.LocalDate;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class Benevole extends Personne {

	private final Property<Permis> permis = new SimpleObjectProperty<>();

	public Benevole() {
	}

	public Benevole(int id, String nom, String prenom, String sexe, LocalDate naissance, String telephone,
			String adresse, int codePostal, String email, String club, String poste) {
		super(id, nom, prenom, sexe, naissance, telephone, adresse, codePostal, email);

	}

}
