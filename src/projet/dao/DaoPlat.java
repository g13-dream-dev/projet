package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Plat;


public class DaoPlat {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer( Plat plat ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;
		
		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO plat (  nom, lieu, date ) VALUES( ?, ?) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, plat.getNom() );
			stmt.setObject( 2, plat.getNombre() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			plat.setId( rs.getObject( 1, Integer.class) );
			return plat.getId();
	
		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public void modifier( Plat plat ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE plat SET nom = ?, nombre = ? WHERE idplat =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, plat.getNom() );
			stmt.setObject( 2, plat.getNombre() );
			stmt.setObject( 3, plat.getId() );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void supprimer( int idPlat ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM plat WHERE idplat = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idPlat );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Plat retrouver( int idPlat ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM plat WHERE idplat = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, idPlat);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construirePlat( rs );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public List<Plat> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM plat ORDER BY nom, nombre";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Plat> plats = new LinkedList<>();
			while (rs.next()) {
				plats.add( construirePlat( rs ) );
			}
			return plats;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Plat construirePlat( ResultSet rs ) throws SQLException {
		Plat plat = new Plat();
		plat.setId( rs.getObject( "idplat", Integer.class ) );
		plat.setNom( rs.getObject( "nom", String.class ) );
		plat.setNombre(rs.getObject("nombre", Integer.class));
		return plat;
	}

}
