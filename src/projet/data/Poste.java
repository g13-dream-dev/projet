package projet.data;

import java.time.LocalTime;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Poste {
	private final Property<Integer> id = new SimpleObjectProperty<>();
	private final StringProperty libelle = new SimpleStringProperty();
	private final Property<Integer> nombrePlaces = new SimpleObjectProperty<>();
	private final Property<LocalTime> heureD = new SimpleObjectProperty<>();
	private final StringProperty etat = new SimpleStringProperty();

	
	public Poste() {
		
	}

	public Poste(int id, String libelle, int nombrePlaces, LocalTime heureD, String etat) {
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
	

	public final Property<LocalTime> heureDProperty() {
		return this.heureD;
	}
	

	public final LocalTime getHeureD() {
		return this.heureDProperty().getValue();
	}
	

	public final void setHeureD(final LocalTime heureD) {
		this.heureDProperty().setValue(heureD);
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

	@Override
	public String toString() {
		return getLibelle() + " (" + getNombrePlaces() + " places) : " + getEtat();
	}
	
	
	
	
}
