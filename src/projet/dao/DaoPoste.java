package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import javafx.fxml.FXML;
import jfox.dao.jdbc.UtilJdbc;
import projet.data.Benevole;
import projet.data.Poste;


public class DaoPoste {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer( Poste poste ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;
		
		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO poste (  libelle, etat, heured, nombreplaces ) VALUES( ?, ?, ?, ?) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, poste.getLibelle() );
			stmt.setObject( 2, poste.getEtat() );
			stmt.setObject( 3, poste.getHeureD() );
			stmt.setObject( 4, poste.getNombrePlaces() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			poste.setId( rs.getObject( 1, Integer.class) );
			return poste.getId();
	
		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public void modifier( Poste poste ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE poste SET libelle = ?, etat = ?, heured = ?, nombreplaces = ? WHERE idposte =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, poste.getLibelle() );
			stmt.setObject( 2, poste.getEtat() );
			stmt.setObject( 3, poste.getNombrePlaces() );
			stmt.setObject( 4, poste.getHeureD() );
			stmt.setObject( 5, poste.getId() );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void supprimer( int idPoste ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM poste WHERE idposte = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idPoste );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Poste retrouver( int idPoste ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM poste WHERE idposte = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, idPoste);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construirePoste( rs );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	@FXML
	public List<Poste> listerRecherche(String txt) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM poste WHERE libelle LIKE ?  ORDER BY libelle, etat, nombreplaces";
			stmt = cn.prepareStatement( sql );
			stmt.setString(1,'%'+txt+'%');
			rs = stmt.executeQuery();

			List<Poste> postes = new LinkedList<>();
			while (rs.next()) {
				postes.add( construirePoste( rs ) );
			}
			return postes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}



	public List<Poste> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM poste ORDER BY libelle, etat, nombreplaces";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Poste> postes = new LinkedList<>();
			while (rs.next()) {
				postes.add( construirePoste( rs ) );
			}
			return postes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public boolean attribuerBenevoleAuPoste( Poste poste , Benevole benevole) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			
			cn = dataSource.getConnection();
			sql = "SELECT count(idposte) as n from attribuer where idposte = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject(1, poste.getId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				if(rs.getObject("n", Integer.class) < poste.getNombrePlaces()) {
					sql = "SELECT idbenevole FROM attribuer where idbenevole = ?";
					stmt = cn.prepareStatement(sql);
					stmt.setObject(1, benevole.getId());
					rs = stmt.executeQuery();
					if(!rs.next()) {
						sql = "INSERT INTO attribuer (idposte, idbenevole) VALUES(?,?)";
						stmt = cn.prepareStatement(sql);
						stmt.setObject(1, poste.getId());
						stmt.setObject(2, benevole.getId());
						stmt.executeUpdate();
					}
				}else {
					return false;
				}
			}else {
				sql = "INSERT INTO attribuer (idposte, idbenevole) VALUES(?,?)";
				stmt = cn.prepareStatement(sql);
				stmt.setObject(1, poste.getId());
				stmt.setObject(2, benevole.getId());
				stmt.executeUpdate();
			}
			return true;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Poste construirePoste( ResultSet rs ) throws SQLException {
		Poste poste = new Poste();
		poste.setId( rs.getObject( "idposte", Integer.class ) );
		poste.setLibelle( rs.getObject( "libelle", String.class ) );
		poste.setNombrePlaces(rs.getObject("nombreplaces", Integer.class));
		poste.setHeureD(rs.getObject("heureD",LocalTime.class));
		poste.setEtat(rs.getObject("etat",String.class));
		return poste;
	}

}
