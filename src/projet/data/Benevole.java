package projet.data;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Benevole extends Personne {

	private final BooleanProperty permanent = new SimpleBooleanProperty();
	private final Property<Permis> permis = new SimpleObjectProperty<>();
	private final Property<Competition> competition = new SimpleObjectProperty<>();

	public Benevole() {
		
	}

	public Benevole(int id, String nom, String prenom, String sexe, LocalDate naissance, String telephone,
			String adresse, int codePostal, String email, String club, String poste) {
		super(id, nom, prenom, sexe, naissance, telephone, adresse, codePostal, email);

	}

	public final Property<Permis> permisProperty() {
		return this.permis;
	}
	

	public final Permis getPermis() {
		return this.permisProperty().getValue();
	}
	

	public final void setPermis(final Permis permis) {
		this.permisProperty().setValue(permis);
	}

	public final BooleanProperty permanentProperty() {
		return this.permanent;
	}
	

	public final boolean isPermanent() {
		return this.permanentProperty().get();
	}
	

	public final void setPermanent(final boolean permanent) {
		this.permanentProperty().set(permanent);
	}

	public final Property<Competition> competitionProperty() {
		return this.competition;
	}
	

	public final Competition getCompetition() {
		return this.competitionProperty().getValue();
	}
	

	public final void setCompetition(final Competition competition) {
		this.competitionProperty().setValue(competition);
	}

}
