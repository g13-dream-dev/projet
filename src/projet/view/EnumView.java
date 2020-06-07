package projet.view;

import javafx.scene.Scene;
import jfox.javafx.view.IEnumView;


public enum EnumView implements IEnumView {

	
	// Valeurs
	
	
	Info				( "systeme/ViewInfo.fxml" ),
	Connexion			( "systeme/ViewConnexion.fxml" ),
	CompteListe			( "compte/ViewCompteListe.fxml" ),
	CompteForm			( "compte/ViewCompteForm.fxml" ),
	PersonneListe		( "personne/ViewPersonneListe.fxml" ),
	PersonneForm		( "personne/ViewPersonneForm.fxml" ),
	TestDaoBenevole	( "test/ViewTestDaoBenevole.fxml" ),
	TestDaoCoureur			( "test/ViewTestDaoCoureur.fxml" ),
	TestDaoCompetition		( "test/ViewTestDaoCompetition.fxml" ),
	CoureurListe		( "coureur/ViewCoureurListe.fxml" ),
	CoureurClubListe		( "coureur/ViewCoureurClubListe.fxml" ),
	CoureurForm       ("coureur/ViewCoureurForm.fxml"),
	BenevoleForm		( "benevole/ViewBenevoleInscription.fxml" ),
	BenevoleListe		( "benevole/ViewBenevoleListe.fxml" ),
	PosteListe	( "poste/ViewPosteListe.fxml" ),
	PosteForm	( "poste/ViewPosteForm.fxml" ),
	CompetitionListe	( "competition/ViewCompetitionListe.fxml" ),
	CompetitionForm	( "competition/ViewCompetitionForm.fxml" ),
	CourseListe	( "competition/ViewCourseListe.fxml" ),
	CourseForm       ("competition/ViewCourseForm.fxml"),
	PlatListe       ("plat/ViewPlatListe.fxml"),
	PlatForm       ("plat/ViewPlatForm.fxml"),
	PosteRechercher     ("poste/ViewPosteRechercher.fxml"),
	PosteAttribuerBenevole	("poste/ViewAttribuerBenevole.fxml"),
	MaterielListe       ("materiel/ViewMaterielListe.fxml"),
	MaterielForm       ("materiel/ViewMaterielForm.fxml")
	;

	
	// Champs
	
	private String		path;
	private Object		controller;
	private Scene		scene;

	
	// Constructeur 
	
	EnumView( String path ) {
		this.path = path;
	}

	
	// Getters & setters

	@Override
	public String getPath() {
		return path;
	}
	
	@Override
	public Object getController() {
		return controller;
	}

	@Override
	public void setController(Object controller) {
		this.controller = controller;
	}
	
	@Override
	public Scene getScene() {
		return scene;
	}
	
	@Override
	public void setScene(Scene scene) {
		this.scene = scene;
	}

}
