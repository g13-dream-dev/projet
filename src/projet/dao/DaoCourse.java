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

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Competition;
import projet.data.Course;


public class DaoCourse {

	
	// Champs

	@Inject
	private DataSource		dataSource;

	
	// Actions

	public void insererPourCompetition( Competition competition ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;
		
		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO course (  idcompetition, nom, heureD, distance,lieudepart,lieuarriv  ) VALUES( ?, ?, ?, ?,?,? ) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			for(Course course : competition.getCourses()) {
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
			}
	
		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public void modifierPourCompetition( Competition competition ) {

		Connection			cn		= null;
		PreparedStatement stmtDelete = null;
		PreparedStatement stmtInsert = null;
		PreparedStatement stmtUpdate = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM telephone WHERE idtelephone = ?";
			stmtDelete = cn.prepareStatement(sql);
			for (Course course : listerPourCompetition(competition)) {
				if (!competition.getCourses().contains(course)) {
					stmtDelete.setObject(1, course.getId());
					stmtDelete.executeUpdate();
				}
			}
			sql = "INSERT INTO course (  idcompetition, nom, heureD, distance,lieudepart,lieuarriv  ) VALUES( ?, ?, ?, ?,?,? ) ";
			stmtInsert = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sql = "UPDATE course SET idcompetition = ?, nom = ?, heureD = ?, distance = ?, lieudepart = ?, lieuarriv = ? WHERE idcourse = ?";
			stmtUpdate = cn.prepareStatement(sql);
			for (Course course : competition.getCourses()) {
				if (course.getId() == null || course.getId() == 0) {
					stmtInsert.setObject(1, competition.getId());
					stmtInsert.setObject(2, course.getNom());
					stmtInsert.setObject(3, course.getHeureD());
					stmtInsert.setObject(4, course.getDistance());
					stmtInsert.setObject(5, course.getLieuDepart());
					stmtInsert.setObject(6, course.getLieuArriv());
					stmtInsert.executeUpdate();
					// Récupère l'identifiant généré par le SGBD
					rs = stmtInsert.getGeneratedKeys();
					rs.next();
					course.setId(rs.getObject(1, Integer.class));
				} else {
					stmtInsert.setObject(1, competition.getId());
					stmtInsert.setObject(2, course.getNom());
					stmtInsert.setObject(3, course.getHeureD());
					stmtInsert.setObject(4, course.getDistance());
					stmtInsert.setObject(5, course.getLieuDepart());
					stmtInsert.setObject(6, course.getLieuArriv());
					stmtInsert.setObject(7, course.getId());
					stmtUpdate.executeUpdate();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmtDelete, stmtInsert, stmtUpdate, cn);
		}
	}
	public void supprimer( int idCourse) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM competition WHERE idcourse = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idCourse );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	public void supprimerPourCompetition( Competition competition ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM competition WHERE idcompetition = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, competition.getId() );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}
	
	public Course retrouver( int idCourse) {

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
			
			if(rs.next()) {
				return construireCourse( rs ) ;
			}else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	public List<Course> listerPourCompetition( Competition competition) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM course WHERE idcompetition = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, competition.getId());
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
	
	public String nombreDeCoursePourCompetition( Competition competition) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM course WHERE idcompetition = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, competition.getId());
			rs = stmt.executeQuery();

			int nb_courses = 0;
			while (rs.next()) {
				nb_courses++;
			}
			return nb_courses + " Courses pour cette compétition";
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
		course.setHeureD( rs.getObject( "heureD", LocalTime.class ) );
		course.setDistance( rs.getObject( "distance", Integer.class ) );
		course.setLieuDepart( rs.getObject( "lieudepart", String.class ) );
		course.setLieuArriv( rs.getObject( "lieuarriv", String.class ) );
		return course;
	}

}
