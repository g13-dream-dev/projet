package projet.data;

import java.time.LocalDate;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Permis {
	private final Property<Integer>		id			= new SimpleObjectProperty<>();
	private final StringProperty		numero		= new SimpleStringProperty();
	private final Property<LocalDate> dateDelivrance = new SimpleObjectProperty<>();
	
	public Permis() {
	}
	
	public Permis(int id, String numero, LocalDate dateDelivrance) {
		setId(id);
		setNumero(numero);
		setDateDelivrance(dateDelivrance);
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
	

	public final StringProperty numeroProperty() {
		return this.numero;
	}
	

	public final String getNumero() {
		return this.numeroProperty().get();
	}
	

	public final void setNumero(final String numero) {
		this.numeroProperty().set(numero);
	}
	

	public final Property<LocalDate> dateDelivranceProperty() {
		return this.dateDelivrance;
	}
	

	public final LocalDate getDateDelivrance() {
		return this.dateDelivranceProperty().getValue();
	}
	

	public final void setDateDelivrance(final LocalDate dateDelivrance) {
		this.dateDelivranceProperty().setValue(dateDelivrance);
	}
	
	
}
