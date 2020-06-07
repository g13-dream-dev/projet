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
			sql = "INSERT INTO poste (  libelle, etat, heured, nombreplaces, placesoccupees ) VALUES( ?, ?, ?, ?, ?) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, poste.getLibelle() );
			stmt.setObject( 2, poste.getEtat() );
			stmt.setObject( 3, poste.getHeureD() );
			stmt.setObject( 4, poste.getNombrePlaces() );
			stmt.setObject( 5, poste.getPlacesOccupees() );
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
			sql = "UPDATE poste SET libelle = ?, etat = ?, heured = ?, nombreplaces = ?, placesoccupees = ? WHERE idposte =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, poste.getLibelle() );
			stmt.setObject( 2, poste.getEtat() );
			stmt.setObject( 3, poste.getHeureD() );
			stmt.setObject( 4, poste.getNombrePlaces() );
			stmt.setObject( 5, poste.getPlacesOccupees() );
			stmt.setObject( 6, poste.getId() );
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
			sql = "SELECT * FROM poste ORDER BY libelle, etat, nombreplaces, placesoccupees";
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
	public List<Poste> listerPourBenevole(Benevole benevole) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT idposte FROM attribuer WHERE idbenevole = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject(1, benevole.getId());
			rs = stmt.executeQuery();

			List<Poste> postes = new LinkedList<>();
			while (rs.next()) {
				postes.add( retrouver( rs.getInt("idposte") ) );
			}
			return postes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	public boolean estDejaAttribue(Poste poste, Benevole benevole) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT count(idbenevole) AS n FROM attribuer WHERE idposte = ? AND idbenevole = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject(1, poste.getId());
			stmt.setObject(2, benevole.getId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt("n") > 0)return true;
			}
			return false;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public void attribuerBenevoleAuPoste( Poste poste , Benevole benevole) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sqlAttribuer;

		try {
			
			cn = dataSource.getConnection();
			sqlAttribuer = "INSERT INTO attribuer (idposte, idbenevole) VALUES(?,?)";
			stmt = cn.prepareStatement(sqlAttribuer);
			stmt.setObject(1, poste.getId());
			stmt.setObject(2, benevole.getId());
			stmt.executeUpdate();
			
			poste.setPlacesOccupees(poste.getPlacesOccupees()+1);
			modifier(poste);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	
	public int nombrePostePour( Benevole Benevole ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT count(idbenevole) as n FROM attribuer WHERE idbenevole = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, Benevole.getId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				int n = rs.getInt("n");
				return n;
			}
			return 0;
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
		poste.setPlacesOccupees(rs.getObject("placesoccupees", Integer.class));
		poste.setHeureD(rs.getObject("heureD",LocalTime.class));
		poste.setEtat(rs.getObject("etat",String.class));
		return poste;
	}

}
