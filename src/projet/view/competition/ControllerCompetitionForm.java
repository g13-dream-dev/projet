package projet.view.competition;

import javax.inject.Inject;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.IManagerGui;
import projet.data.Competition;
import projet.view.EnumView;

public class ControllerCompetitionForm {

	// Composants de la vue

	@FXML
	private TextField textFieldId;
	@FXML
	private TextField textFieldNom;
	@FXML
	private TextField textFieldLieu;
	@FXML
	private DatePicker datePickerDate;
	@FXML
	private Button listerCourses;

	// Autres champs

	@Inject
	private IManagerGui managerGui;
	@Inject
	private ModelCompetition modelCompetition;
	@Inject
	private ModelCourse modelCourse;

	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding

		Competition courant = modelCompetition.getCourant();
		

		textFieldId.textProperty().bindBidirectional(courant.idProperty(), new IntegerStringConverter());
		textFieldNom.textProperty().bindBidirectional(courant.nomProperty());
		textFieldLieu.textProperty().bindBidirectional(courant.lieuProperty());
		UtilFX.bindBidirectional(datePickerDate.getEditor(), courant.dateProperty(), new ConverterStringLocalDate());
		listerCourses.textProperty().bindBidirectional(modelCourse.nombreCoursesProperty());
	}

	// Actions
	@FXML
	private void doListerCourses() {
		System.out.println(modelCompetition.getCourant().idProperty().getValue());
		if (modelCompetition.getCourant().idProperty().getValue() != null) {
			managerGui.showView(EnumView.CourseListe);
		} else {
			managerGui.showDialogMessage(
					"Aucune course n'est ratachée a cette competition car elle n'est pas encore enregistrée!");
		}

	}

	@FXML
	private void doValider() {
		modelCompetition.validerMiseAJour();
		managerGui.showView(EnumView.CompetitionListe);
	}

	@FXML
	private void doAnnuler() {
		managerGui.showView(EnumView.CompetitionListe);
	}

	// methodes de fonctionnalités
	@FXML
	private void doListerToutesLesCompetitions() {
		modelCompetition.actualiserListe();
		managerGui.showView(EnumView.CompetitionListe);
	}

	@FXML
	private void doAjouterUneCompetition() {
		modelCompetition.preparerAjouter();
		managerGui.showView(EnumView.CompetitionForm);
	}
}
