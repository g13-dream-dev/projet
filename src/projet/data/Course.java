package projet.data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {
	private final Property<Integer> id = new SimpleObjectProperty<>();
	private final StringProperty libelle = new SimpleStringProperty();
	private final Property<Integer> distance= new SimpleObjectProperty<>();
	private final StringProperty heureD = new SimpleStringProperty();


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
	


	public final StringProperty libelleProperty() {
		return this.libelle;
	}
	


	public final String getLibelle() {
		return this.libelleProperty().get();
	}
	


	public final void setLibelle(final String libelle) {
		this.libelleProperty().set(libelle);
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
	

	
	
	
}
