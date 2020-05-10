package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Coureur;
import projet.data.Personne;


public class DaoCoureur {

	
	// Champs

	@Inject
	private DataSource		dataSource;
	@Inject
	private DaoPersonne daoPersonne;
	// Actions

	public int inserer(Coureur coureur)  {
		daoPersonne.inserer(coureur);
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			// Insère le coureur
			sql = "INSERT INTO coureur ( idpersonne, club, poste) VALUES ( ?, ?, ?)";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS  );
			stmt.setObject(	1, coureur.getId() );
			stmt.setString(	2, coureur.getClub() );
			stmt.setString(	3, coureur.getPoste() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			//rs = stmt.getGeneratedKeys();
			//rs.next();
			//coureur.setId( rs.getObject( 1, Integer.class ) );
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
		
		// Retourne l'identifiant
		return coureur.getId();
	}

	
	public void modifier(Coureur coureur)  {
		daoPersonne.modifier(coureur);
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Modifie le coureur
			sql = "UPDATE coureur SET club = ?, poste = ? WHERE idpersonne =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, coureur.getClub());
			stmt.setObject( 2, coureur.getPoste());
			stmt.setObject( 3, coureur.getId() );
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public void supprimer(int idCoureur)  {
		daoPersonne.supprimer(idCoureur);
		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String 				sql;

		try {
			cn = dataSource.getConnection();

			// Supprime le coureur
			sql = "DELETE FROM coureur WHERE idpersonne = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject( 1, idCoureur );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Coureur retrouver(int idCoureur)  {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM coureur WHERE idpersonne = ?";
            stmt = cn.prepareStatement(sql);
            stmt.setObject( 1, idCoureur);
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                return construireCoureur(rs, true );
            } else {
            	return null;
            }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}

	   
	public List<Coureur> listerTout()   {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs 		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM coureur ORDER BY club, poste";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			List<Coureur> coureurs = new ArrayList<>();
			while (rs.next()) {
				coureurs.add( construireCoureur(rs, false) );
			}
			return coureurs;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	// Méthodes auxiliaires
	
	private Coureur construireCoureur( ResultSet rs, boolean flagComplet ) throws SQLException {

		Personne personne = daoPersonne.retrouver(rs.getObject("idpersonne", Integer.class));
		Coureur coureur = new Coureur();
		coureur.setId(personne.getId());
		coureur.setNom(personne.getNom());
		coureur.setPrenom(personne.getPrenom());
		coureur.setSexe(personne.getSexe());
		coureur.setNaissance(personne.getNaissance());
		coureur.setAdresse(personne.getAdresse());
		coureur.setCodePostal(personne.getCodePostal());
		coureur.setTelephone(personne.getTelephone());
		coureur.setEmail(personne.getEmail());
		coureur.setClub(rs.getObject("club", String.class));
		coureur.setPoste(rs.getObject("poste", String.class));
		
		return coureur;
	}
	
}
