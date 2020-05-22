package projet.data;

import java.time.LocalTime;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {
	private final Property<Integer> id = new SimpleObjectProperty<>();
	private final StringProperty nom = new SimpleStringProperty();
	private final Property<Integer> distance = new SimpleObjectProperty<>();
	private final Property<LocalTime> heureD = new SimpleObjectProperty<>();
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

	public final Property<LocalTime> heureDProperty() {
		return this.heureD;
	}

	public final LocalTime getHeureD() {
		return this.heureDProperty().getValue();
	}

	public final void setHeureD(final LocalTime heureD) {
		this.heureDProperty().setValue(heureD);
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Course) {
			Course course = (Course) obj;
			if(course.getDistance().equals(this.getDistance()) && course.getHeureD().equals(this.getHeureD())
					&& course.getLieuDepart().equals(this.lieuDepart) && course.getLieuArriv().equals(this.getLieuArriv())
					&& course.getNom().equals(this.getNom()))return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
