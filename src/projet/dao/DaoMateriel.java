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
import projet.data.Materiel;


public class DaoMateriel {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer( Materiel materiel ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;
		
		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO materiel (  nom, lieu, date ) VALUES( ?, ?) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, materiel.getNom() );
			stmt.setObject( 2, materiel.getNombre() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			materiel.setId( rs.getObject( 1, Integer.class) );
			return materiel.getId();
	
		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public void modifier( Materiel materiel ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE materiel SET nom = ?, nombre = ? WHERE idmateriel =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, materiel.getNom() );
			stmt.setObject( 2, materiel.getNombre() );
			stmt.setObject( 3, materiel.getId() );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void supprimer( int idMateriel ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM materiel WHERE idmateriel = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idMateriel );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Materiel retrouver( int idMateriel ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM materiel WHERE idmateriel = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, idMateriel);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireMateriel( rs );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public List<Materiel> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM materiel ORDER BY nom, nombre";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Materiel> materiels = new LinkedList<>();
			while (rs.next()) {
				materiels.add( construireMateriel( rs ) );
			}
			return materiels;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Materiel construireMateriel( ResultSet rs ) throws SQLException {
		Materiel materiel = new Materiel();
		materiel.setId( rs.getObject( "idmateriel", Integer.class ) );
		materiel.setNom( rs.getObject( "nom", String.class ) );
		materiel.setNombre(rs.getObject("nombre", Integer.class));
		return materiel;
	}

}
