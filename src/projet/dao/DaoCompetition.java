package projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import jfox.dao.jdbc.UtilJdbc;
import projet.data.Competition;
import projet.data.Course;


public class DaoCompetition {

	
	// Champs

	@Inject
	private DataSource		dataSource;
	@Inject
	private DaoCourse daoCourse;

	
	// Actions

	public int inserer( Competition competition ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;
		
		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO competition (  nom, lieu, date ) VALUES( ?, ?, ? ) ";
			stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
			stmt.setObject( 1, competition.getNom() );
			stmt.setObject( 2, competition.getLieu() );
			stmt.setObject( 3, competition.getDate() );
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			competition.setId( rs.getObject( 1, Integer.class) );
			return competition.getId();
	
		} catch ( SQLException e ) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public void modifier( Competition competition ) {

		Connection			cn		= null;
		PreparedStatement	stmt	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE competition SET nom = ?, lieu = ?, date = ? WHERE idcompetition =  ?";
			stmt = cn.prepareStatement( sql );
			stmt.setObject( 1, competition.getNom() );
			stmt.setObject( 2, competition.getLieu() );
			stmt.setObject( 3, competition.getDate() );
			stmt.setObject( 4, competition.getId() );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}


	public void supprimer( int idCompetition ) {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM competition WHERE idcompetition = ? ";
			stmt = cn.prepareStatement( sql );
			stmt.setInt( 1, idCompetition );
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( stmt, cn );
		}
	}

	
	public Competition retrouver( int idCompetition ) {

		Connection			cn 		= null;
		PreparedStatement	stmt	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM competition WHERE idcompetition = ?";
			stmt = cn.prepareStatement( sql );
			stmt.setInt(1, idCompetition);
			rs = stmt.executeQuery();

			if ( rs.next() ) {
				return construireCompetition( rs );
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}


	public List<Competition> listerTout() {

		Connection			cn 		= null;
		PreparedStatement	stmt 	= null;
		ResultSet 			rs		= null;
		String				sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM competition ORDER BY date desc";
			stmt = cn.prepareStatement( sql );
			rs = stmt.executeQuery();

			List<Competition> competitions = new LinkedList<>();
			while (rs.next()) {
				competitions.add( construireCompetition( rs ) );
			}
			return competitions;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close( rs, stmt, cn );
		}
	}
	
	
	// Méthodes auxiliaires
	
	private Competition construireCompetition( ResultSet rs ) throws SQLException {
		Competition competition = new Competition();
		competition.setId( rs.getObject( "idcompetition", Integer.class ) );
		competition.setNom( rs.getObject( "nom", String.class ) );
		competition.setLieu(rs.getObject("lieu", String.class));
		competition.setDate(rs.getObject("date", LocalDate.class));
		List<Course> courses = daoCourse.listerPourCompetition(competition);
		competition.setCourses(courses);
		return competition;
	}

}
