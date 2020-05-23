package projet.view.course;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalTimeStringConverter;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ConverterStringLocalDate;
import jfox.javafx.util.ListenerFocusValidation;
import jfox.javafx.view.IManagerGui;
import projet.data.Course;
import projet.view.EnumView;


public class ControllerCourseForm {

	
	// Composants de la vue
	
	@FXML
	private TextField		textFieldId;
	@FXML
	private TextField		textFieldNom;
	@FXML
	private TextField		textFieldHeureD;
	@FXML
	private TextField		textFieldDistance;
	@FXML
	private TextField		textFieldLieuDepart;
	@FXML
	private TextField		textFieldLieuArriv;
	
	
	

	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelCourse	modelCourse;


	// Initialisation du Controller

	@FXML
	private void initialize() {

		// Data binding
		
		Course courant = modelCourse.getCourant();

		textFieldId.textProperty().bindBidirectional( courant.idProperty(), new IntegerStringConverter()  );

		textFieldNom.textProperty().bindBidirectional( courant.nomProperty() );
		
		textFieldHeureD.textProperty().bindBidirectional( courant.heureDProperty(), new LocalTimeStringConverter());
		
		textFieldDistance.textProperty().bindBidirectional( courant.distanceProperty(), new IntegerStringConverter()  );
		
		textFieldLieuDepart.textProperty().bindBidirectional( courant.lieuDepartProperty());
		
		textFieldLieuArriv.textProperty().bindBidirectional( courant.lieuArrivProperty());
		
	}
	
	
	// Actions
	@FXML
	private void doValider() {
		modelCourse.validerMiseAJour();
		managerGui.showView( EnumView.CourseListe );
	}
	@FXML
	private void doAnnuler() {
		managerGui.showView( EnumView.CourseListe );
	}
}
