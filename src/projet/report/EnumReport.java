package projet.report;


public enum EnumReport implements IEnumReport {

	
	// Valeurs
	
	EtatBenevoleListeSimple		( "benevole/ListeSimple.jrxml" ),
	EtatCoureurListeSimple		( "coureur/ListeSimple.jrxml" ),
	EtatCompetitionListeSimple		( "competition/ListeSimple.jrxml" ),
	;

	
	// Champs
	
	private String		path;

	
	// Constructeur 
	
	EnumReport( String path ) {
		this.path = path;
	}

	
	// Getters & setters

	@Override
	public String getPath() {
		return path;
	}

}
