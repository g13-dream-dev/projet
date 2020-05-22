package projet.data;

import java.time.LocalDate;
import java.util.List;

import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Competition {
	private final Property<Integer> id = new SimpleObjectProperty<>();
	private final StringProperty nom = new SimpleStringProperty();
	private final StringProperty lieu = new SimpleStringProperty();
	private final Property<LocalDate> date = new SimpleObjectProperty<>();
	private final ObservableList<Course>	courses	= FXCollections.observableArrayList(
				t ->  new Observable[] { t.nomProperty(), t.distanceProperty() } 
			);

	public Competition() {
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

	public final StringProperty lieuProperty() {
		return this.lieu;
	}

	public final String getLieu() {
		return this.lieuProperty().get();
	}

	public final void setLieu(final String lieu) {
		this.lieuProperty().set(lieu);
	}

	public final Property<LocalDate> dateProperty() {
		return this.date;
	}

	public final LocalDate getDate() {
		return this.dateProperty().getValue();
	}

	public final void setDate(final LocalDate date) {
		this.dateProperty().setValue(date);
	}

	public ObservableList<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses.clear();
		this.courses.setAll(courses);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Competition) {
			Competition competition = (Competition) obj;
			if (competition.getNom().equals(this.getNom()) && competition.getDate().equals(this.getDate()))
				return true;
		}
		return false;
	}

}