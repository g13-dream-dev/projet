package projet.view;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import jfox.javafx.view.IManagerGui;
import projet.commun.Roles;
import projet.data.Compte;
import projet.report.EnumReport;
import projet.report.ManagerReport;
import projet.view.systeme.ModelConnexion;

public class MenuBarAppli extends MenuBar {

	// Champs

	private Menu menuDonnees;
	private Menu menuEtats;
	private Menu menuTests;

	private MenuItem itemDeconnecter;

	private MenuItem itemCategories;
	private MenuItem itemComptes;
	private MenuItem itemCompetition;
	private MenuItem itemCoureur;
	private MenuItem itemBenevole;
	private MenuItem itemPoste;
	private MenuItem itemPlat;
	private MenuItem itemMateriel;

	@Inject
	private IManagerGui managerGui;
	@Inject
	private ManagerReport managerReport;
	@Inject
	private ModelConnexion modelConnexion;

	// Initialisation

	@PostConstruct
	public void init() {

		// Variables de travail
		Menu menu;
		MenuItem item;

		// Manu Système

		menu = new Menu("Système");
		this.getMenus().add(menu);

		item = new MenuItem("Se déconnecter");
		item.setOnAction((e) -> managerGui.showView(EnumView.Connexion));
		menu.getItems().add(item);
		itemDeconnecter = item;

		item = new MenuItem("Quitter");
		item.setOnAction((e) -> managerGui.exit());
		menu.getItems().add(item);

		// Manu Données

		menu = new Menu("Données");
		this.getMenus().add(menu);
		menuDonnees = menu;

		item = new MenuItem("Competitions");
		item.setOnAction((e) -> managerGui.showView(EnumView.CompetitionListe));
		menu.getItems().add(item);
		itemCompetition = item;

		item = new MenuItem("Coureurs");
		item.setOnAction((e) -> managerGui.showView(EnumView.CoureurClubListe));
		menu.getItems().add(item);
		itemCoureur = item;

		item = new MenuItem("Benevoles");
		item.setOnAction((e) -> managerGui.showView(EnumView.BenevoleListe));
		menu.getItems().add(item);
		itemBenevole = item;

		item = new MenuItem("Postes");
		item.setOnAction((e) -> managerGui.showView(EnumView.PosteListe));
		menu.getItems().add(item);
		itemPoste = item;

		item = new MenuItem("Plat");
		item.setOnAction((e) -> managerGui.showView(EnumView.PlatListe));
		menu.getItems().add(item);
		itemPlat = item;
		
		item = new MenuItem("Materiel");
		item.setOnAction((e) -> managerGui.showView(EnumView.MaterielListe));
		menu.getItems().add(item);
		itemMateriel = item;
		
		item = new MenuItem("Comptes");
		item.setOnAction((e) -> managerGui.showView(EnumView.PlatListe));
		menu.getItems().add(item);
		itemComptes = item;

		// Manu Etats

		menu = new Menu("Etats");
		this.getMenus().add(menu);
		menuEtats = menu;

		item = new MenuItem("Liste simple des coureurs");
		item.setOnAction((e) -> managerReport.showViewer(EnumReport.EtatCoureurListeSimple, null));
		menu.getItems().add(item);

		item = new MenuItem("Liste simple des bénévoles");
		item.setOnAction((e) -> managerReport.showViewer(EnumReport.EtatBenevoleListeSimple, null));
		menu.getItems().add(item);

		item = new MenuItem("Liste simple des compétitions");
		item.setOnAction((e) -> managerReport.openFilePdf(EnumReport.EtatCompetitionListeSimple, null));
		menu.getItems().add(item);

		/*item = new MenuItem("Liste des personnes (viewer)");
		item.setOnAction((e) -> managerReport.showViewer(EnumReport.PersonnesListeSimple, null));
		menu.getItems().add(item);

		item = new MenuItem("Annuaire téléphonique");
		item.setOnAction((e) ->
//				managerReport.print( EnumReport.AnnuaireTelephone, null ) );
		managerReport.showViewer(EnumReport.AnnuaireTelephone, null));
		menu.getItems().add(item);*/

		// Manu Tests

		menu = new Menu("Tests");
		;
		this.getMenus().add(menu);
		menuTests = menu;

		item = new MenuItem("DaoCompetition");
		item.setOnAction((e) -> managerGui.showView(EnumView.TestDaoCompetition));
		menu.getItems().add(item);

		item = new MenuItem("DaoCoureur");
		item.setOnAction((e) -> managerGui.showView(EnumView.TestDaoCoureur));
		menu.getItems().add(item);

		item = new MenuItem("DaoBenevole");
		item.setOnAction((e) -> managerGui.showView(EnumView.TestDaoBenevole));
		menu.getItems().add(item);
		
		
		//Menu aide
		menu = new Menu("Aide ?");
		this.getMenus().add(menu);

		// Configuration initiale du menu
		configurerMenu(modelConnexion.getCompteActif());

		// Le changement du compte connecté modifie automatiquement le menu
		modelConnexion.compteActifProperty().addListener((obs) -> {
			Platform.runLater(() -> configurerMenu(modelConnexion.getCompteActif()));
		});

	}

	// Méthodes auxiliaires

	private void configurerMenu(Compte compteActif) {

		itemDeconnecter.setDisable(true);

		menuDonnees.setVisible(false);
		itemComptes.setVisible(false);
		itemCompetition.setVisible(false);
		itemCoureur.setVisible(false);
		itemBenevole.setVisible(false);
		itemPoste.setVisible(false);
		itemPlat.setVisible(false);

		if (compteActif != null) {
			itemDeconnecter.setDisable(false);
			if (compteActif.isInRole(Roles.UTILISATEUR)) {
				menuDonnees.setVisible(true);
			}
			if (compteActif.isInRole(Roles.ADMINISTRATEUR)) {
				menuDonnees.setVisible(true);
				itemComptes.setVisible(true);
				itemCompetition.setVisible(true);
				itemCoureur.setVisible(true);
				itemBenevole.setVisible(true);
				itemPoste.setVisible(true);
				itemPlat.setVisible(true);
			}
		}
	}

}
