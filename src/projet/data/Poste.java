package projet.data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Poste {
	private final Property<Integer> id = new SimpleObjectProperty<>();
	private final StringProperty libelle = new SimpleStringProperty();
	private final Property<Integer> nombrePlaces = new SimpleObjectProperty<>();
	private final StringProperty heureD = new SimpleStringProperty();
	private final StringProperty etat = new SimpleStringProperty();

	public Poste() {
	}

	public Poste(int id, String libelle, int nombrePlaces, String heureD, String etat) {
		setId(id);
		setLibelle(libelle);
		setNombrePlaces(nombrePlaces);
		setHeureD(heureD);
		setEtat(etat);
	}

	public final Property<Integer> idProperty() {
		return this.id;
	}
	

	public final Integer getId() {
		return this.idProperty().getValue();
	}
	

	public final void setId(final Integer id) {
		this.idProperty().setValue(id);
	}
	

	public final StringProperty libelleProperty() {
		return this.libelle;
	}
	

	public final String getLibelle() {
		return this.libelleProperty().get();
	}
	

	public final void setLibelle(final String libelle) {
		this.libelleProperty().set(libelle);
	}
	

	public final Property<Integer> nombrePlacesProperty() {
		return this.nombrePlaces;
	}
	

	public final Integer getNombrePlaces() {
		return this.nombrePlacesProperty().getValue();
	}
	

	public final void setNombrePlaces(final Integer nombrePlaces) {
		this.nombrePlacesProperty().setValue(nombrePlaces);
	}
	

	public final StringProperty heureDProperty() {
		return this.heureD;
	}
	

	public final String getHeureD() {
		return this.heureDProperty().get();
	}
	

	public final void setHeureD(final String heureD) {
		this.heureDProperty().set(heureD);
	}
	

	public final StringProperty etatProperty() {
		return this.etat;
	}
	

	public final String getEtat() {
		return this.etatProperty().get();
	}
	

	public final void setEtat(final String etat) {
		this.etatProperty().set(etat);
	}
	
	
}
