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
import projet.data.Course;


public class DaoCourse {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public int inserer( Course course ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;
		
		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO course (  nom, heureD, distance,lieudepart,lieuarriv  ) VALUES( ?, ?, ?,?,? ) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, course.getNom() );
			stmt.setObject( 2, course.getHeureD() );
			stmt.setObject( 3, course.getDistance() );
			stmt.setObject( 4, course.getLieuDepart());
			stmt.setObject( 5, course.getLieuArriv() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			course.setId( rs.getObject( 1, Integer.class) );
			return course.getId();
	
		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public void modifier( Course course ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE course SET nom = ?, heureD = ?, distance = ?,lieudepart=?,lieuarriv=? WHERE idcourse =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, course.getNom() );
			stmt.setObject( 2, course.getHeureD() );
			stmt.setObject( 3, course.getDistance() );
			stmt.setObject( 4, course.getLieuDepart() );
			stmt.setObject( 5, course.getLieuArriv() );
			stmt.setObject( 6, course.getId() );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void supprimer( int idCourse ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM course WHERE idcourse = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idCourse );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Course retrouver( int idCourse ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM course WHERE idcourse = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, idCourse);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireCourse( rs );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public List<Course> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM course ORDER BY nom";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Course> courses = new LinkedList<>();
			while (rs.next()) {
				courses.add( construireCourse( rs ) );
			}
			return courses;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Course construireCourse( ResultSet rs ) throws SQLException {
		Course course = new Course();
		course.setId( rs.getObject( "idcourse", Integer.class ) );
		course.setNom( rs.getObject( "nom", String.class ) );
		course.setHeureD( rs.getObject( "heureD", String.class ) );
		course.setDistance( rs.getObject( "distance", Integer.class ) );
		course.setLieuDepart( rs.getObject( "lieudepart", String.class ) );
		course.setLieuArriv( rs.getObject( "lieuarriv", String.class ) );
		return course;
	}

}
