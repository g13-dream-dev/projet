package projet.view.course;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import jfox.javafx.util.ConverterStringInteger;
import jfox.javafx.util.ListenerFocusValidation;
import jfox.javafx.view.IManagerGui;
import projet.data.Course;
import projet.view.EnumView;


public class ControllerCourseApercu {

	
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
		
		textFieldHeureD.textProperty().bindBidirectional( courant.heureDProperty());
		
		textFieldDistance.textProperty().bindBidirectional( courant.distanceProperty(), new IntegerStringConverter()  );
		
		textFieldLieuDepart.textProperty().bindBidirectional( courant.lieuDepartProperty());
		
		textFieldLieuArriv.textProperty().bindBidirectional( courant.lieuArrivProperty());
		
	}
	
	
	// Actions
	@FXML
	private void doModifier() {
		modelCourse.preparerAjouter();;
		managerGui.showView( EnumView.CourseForm );
	}
}
