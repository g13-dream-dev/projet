package projet.data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {
	private final Property<Integer> id = new SimpleObjectProperty<>();
	private final StringProperty nom = new SimpleStringProperty();
	private final Property<Integer> distance= new SimpleObjectProperty<>();
	private final StringProperty heureD = new SimpleStringProperty();
	private final StringProperty lieuDepart = new SimpleStringProperty();
	private final StringProperty lieuArriv = new SimpleStringProperty();


	public Course() {
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
	


	public final StringProperty nomProperty() {
		return this.nom;
	}
	


	public final String getNom() {
		return this.nomProperty().get();
	}
	


	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}
	


	public final Property<Integer> distanceProperty() {
		return this.distance;
	}
	


	public final Integer getDistance() {
		return this.distanceProperty().getValue();
	}
	


	public final void setDistance(final Integer distance) {
		this.distanceProperty().setValue(distance);
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


	public final StringProperty lieuDepartProperty() {
		return this.lieuDepart;
	}
	


	public final String getLieuDepart() {
		return this.lieuDepartProperty().get();
	}
	


	public final void setLieuDepart(final String lieuDepart) {
		this.lieuDepartProperty().set(lieuDepart);
	}
	


	public final StringProperty lieuArrivProperty() {
		return this.lieuArriv;
	}
	


	public final String getLieuArriv() {
		return this.lieuArrivProperty().get();
	}
	


	public final void setLieuArriv(final String lieuArriv) {
		this.lieuArrivProperty().set(lieuArriv);
	}
	
	

	
	
	
}
