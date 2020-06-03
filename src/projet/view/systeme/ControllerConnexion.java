package projet.view.systeme;

import javax.inject.Inject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jfox.javafx.view.IManagerGui;
import projet.data.Compte;
import projet.view.EnumView;


public class ControllerConnexion {
	

	// Composants de la vue
	
	@FXML
	private TextField		fieldPseudo;
	@FXML
	private PasswordField	fieldMotDePasse;

	
	// Autres champs
	
	@Inject
	private IManagerGui		managerGui;
	@Inject
	private ModelConnexion	modelConnexion;
	@Inject
	private ModelInfo		modelInfo;
	@FXML
	private ImageView    	logo;
	
	
	// Initialisation du Controller
	
	@FXML
	private void initialize() {
		
		// Data binding
		Compte courant = modelConnexion.getCourant();
		fieldPseudo.textProperty().bindBidirectional( courant.pseudoProperty() );
		fieldMotDePasse.textProperty().bindBidirectional( courant.motDePasseProperty() );
		
		try
		{
			Image image = new Image("https://drive.google.com/open?id=1UqywkRRMz0ykJbooq338nhHLx1W_VFEJ");
			logo.setImage(image);
			//logo.setId("https://drive.google.com/open?id=1UqywkRRMz0ykJbooq338nhHLx1W_VFEJ");
			System.out.println("good");
		}
		catch(Exception e) {
			
			System.out.println(" erreur lors du chargement du logo");
			
		}
		
		

	}
	
	
	public void refresh() {
		// Ferem la session si elle est ouverte
		if ( modelConnexion.getCompteActif() != null ) {
			modelConnexion.fermerSessionUtilisateur();
		}
	}
	

	// Actions
	
	@FXML
	private void doConnexion() {
		managerGui.execTask( () -> {
			modelConnexion.ouvrirSessionUtilisateur();
			Platform.runLater( () -> {
         			modelInfo.titreProperty().setValue( "Bienvenue" );
        			modelInfo.messageProperty().setValue( "Connexion réussie" );
        			managerGui.showView(EnumView.Info);
            }) ;
		} );
	}
	

}
