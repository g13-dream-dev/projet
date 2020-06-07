package projet.data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Materiel {
	private final Property<Integer> id = new SimpleObjectProperty<>();
	private final StringProperty nom = new SimpleStringProperty();
	private final Property<Integer> nombre = new SimpleObjectProperty<>();
	private final Property<Integer> nombreDistribue = new SimpleObjectProperty<>();

	public Materiel() {

	}
	
	public Materiel(int id, String nom, int nombre) {
		setId(id);
		setNom(nom);
		setNombre(nombre);
		setNombreDistribue(0);
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

	public final Property<Integer> nombreProperty() {
		return this.nombre;
	}

	public final Integer getNombre() {
		return this.nombreProperty().getValue();
	}

	public final void setNombre(final Integer nombre) {
		this.nombreProperty().setValue(nombre);
	}

	@Override
	public String toString() {
		return getNom() +"\t(Nombre total : "+ getNombre()+ " / nombre restant : " + (getNombre()-getNombreDistribue());
	}

	public final Property<Integer> nombreDistribueProperty() {
		return this.nombreDistribue;
	}
	

	public final Integer getNombreDistribue() {
		return this.nombreDistribueProperty().getValue();
	}
	

	public final void setNombreDistribue(final Integer nombreDistribue) {
		this.nombreDistribueProperty().setValue(nombreDistribue);
	}
	

}
