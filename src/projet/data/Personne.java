package projet.data;

import java.time.LocalDate;
import java.util.Objects;

import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Personne {


	// Données observables
	
	private final Property<Integer>		id			= new SimpleObjectProperty<>();
	private final StringProperty		nom	 		= new SimpleStringProperty();
	private final StringProperty		prenom		= new SimpleStringProperty();
	private final StringProperty		sexe		= new SimpleStringProperty();
	private final Property<LocalDate> naissance = new SimpleObjectProperty<>();
	private final StringProperty		telephone		= new SimpleStringProperty();
	private final StringProperty		adresse		= new SimpleStringProperty();
	private final Property<Integer>		codePostal			= new SimpleObjectProperty<>();
	private final StringProperty		email		= new SimpleStringProperty();
	//private final ObservableList<Telephone>	telephones	= FXCollections.observableArrayList(
	//		t ->  new Observable[] { t.libelleProperty(), t.numeroProperty() } 
	//	);
	
	
	// Constructeurs
	
	public Personne() {
	}
	
	public Personne( int id, String nom, String prenom, String sexe, LocalDate naissance,String telephone, String adresse, int codePostal, String email) {
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setSexe(sexe);
		setNaissance(naissance);
		setTelephone(telephone);
		setAdresse(adresse);
		setCodePostal(codePostal);
		setEmail(email);
	}
	
	
	// Getters & setters

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
	
	public final java.lang.String getNom() {
		return this.nomProperty().getValue();
	}
	
	public final void setNom(final java.lang.String nom) {
		this.nomProperty().setValue(nom);
	}
	
	public final StringProperty prenomProperty() {
		return this.prenom;
	}
	
	public final java.lang.String getPrenom() {
		return this.prenomProperty().getValue();
	}
	
	public final void setPrenom(final java.lang.String prenom) {
		this.prenomProperty().setValue(prenom);
	}

	// toString()
	
	@Override
	public String toString() {
		return getNom() + " " + getPrenom();
	}
	
	
	// hashCode() & equals()

	@Override
	public int hashCode() {
		return Objects.hash(id.getValue() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		return Objects.equals(id.getValue(), other.id.getValue() );
	}

	public final StringProperty sexeProperty() {
		return this.sexe;
	}
	

	public final String getSexe() {
		return this.sexeProperty().get();
	}
	

	public final void setSexe(final String sexe) {
		this.sexeProperty().set(sexe);
	}
	

	public final Property<LocalDate> naissanceProperty() {
		return this.naissance;
	}
	

	public final LocalDate getNaissance() {
		return this.naissanceProperty().getValue();
	}
	

	public final void setNaissance(final LocalDate naissance) {
		this.naissanceProperty().setValue(naissance);
	}
	

	public final StringProperty adresseProperty() {
		return this.adresse;
	}
	

	public final String getAdresse() {
		return this.adresseProperty().get();
	}
	

	public final void setAdresse(final String adresse) {
		this.adresseProperty().set(adresse);
	}
	

	public final Property<Integer> codePostalProperty() {
		return this.codePostal;
	}
	

	public final Integer getCodePostal() {
		return this.codePostalProperty().getValue();
	}
	

	public final void setCodePostal(final Integer codePostal) {
		this.codePostalProperty().setValue(codePostal);
	}

	public final StringProperty emailProperty() {
		return this.email;
	}
	

	public final String getEmail() {
		return this.emailProperty().get();
	}
	

	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}

	public final StringProperty telephoneProperty() {
		return this.telephone;
	}
	

	public final String getTelephone() {
		return this.telephoneProperty().get();
	}
	

	public final void setTelephone(final String telephone) {
		this.telephoneProperty().set(telephone);
	}
	
	
	
	
}
