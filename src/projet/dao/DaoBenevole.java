package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Benevole;
import projet.data.Permis;
import projet.data.Personne;


public class DaoBenevole {

	
	// Champs

	@Inject
	private DataSource		dataSource;
	@Inject
	private DaoPersonne daoPersonne;
	@Inject
	private DaoPermis daoPermis;
	@Inject DaoCompetition daoCompetition;
	// Actions

	public int inserer(Benevole benevole)  {
		daoPersonne.inserer(benevole);
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le benevole
			sql = "INSERT INTO benevole ( idbenevole, permanent, idcompetition) VALUES ( ?, ?, ?)";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setObject(	1, benevole.getId() );
			stmt.setObject(	2, benevole.isPermanent() );
			stmt.setObject(	3, benevole.getCompetition().getId() );
			stmt.executeUpdate();
			
			if(benevole.getPermis() != null && benevole.getPermis().getNumero() != null) {
				daoPermis.insererPourBenevole(benevole);
			}
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		
		// Retourne l'identifiant
		return benevole.getId();
	}

	
	public void modifier(Benevole benevole)  {
		daoPersonne.modifier(benevole);
		daoPermis.modifierPourBenevole(benevole);
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le benevole
			sql = "UPDATE benevole SET permanent = ?, idcompetition = ? WHERE idbenevole =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, benevole.isPermanent());
			stmt.setObject( 2, benevole.getCompetition().getId());
			stmt.setObject( 3, benevole.getId() );
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public void supprimer(int idBenevole)  {
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Supprime le benevole
			sql = "DELETE FROM benevole WHERE idbenevole = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idBenevole );
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		daoPersonne.supprimer(idBenevole);
	}

	
	public Benevole retrouver(int idBenevole)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM benevole WHERE idbenevole = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idBenevole);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireBenevole(rs, true );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	   
	public List<Benevole> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM benevole";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Benevole> benevoles = new ArrayList<>();
			while (rs.next()) {
				benevoles.add( construireBenevole(rs, false) );
			}
			return benevoles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public List<Benevole> listerPermanent(Boolean permanence)   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM benevole WHERE permanent = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, permanence);
			rs = stmt.executeQuery();
			
			List<Benevole> benevoles = new ArrayList<>();
			while (rs.next()) {
				benevoles.add( construireBenevole(rs, false) );
			}
			return benevoles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	// Méthodes auxiliaires
	
	private Benevole construireBenevole( ResultSet rs, boolean flagComplet ) throws SQLException {

		Personne personne = daoPersonne.retrouver(rs.getObject("idbenevole", Integer.class));
		Benevole benevole = new Benevole();
		benevole.setId(personne.getId());
		benevole.setNom(personne.getNom());
		benevole.setPrenom(personne.getPrenom());
		benevole.setSexe(personne.getSexe());
		benevole.setNaissance(personne.getNaissance());
		benevole.setAdresse(personne.getAdresse());
		benevole.setCodePostal(personne.getCodePostal());
		benevole.setTelephone(personne.getTelephone());
		benevole.setEmail(personne.getEmail());
		benevole.setPermanent(rs.getObject("permanent", Boolean.class));
		benevole.setCompetition(daoCompetition.retrouver(rs.getObject("idcompetition", Integer.class)));
		Permis permis = daoPermis.avoirPourBenevole(benevole);
		if(permis == null)permis = new Permis();
		benevole.setPermis(permis);
		return benevole;
	}
	
}
