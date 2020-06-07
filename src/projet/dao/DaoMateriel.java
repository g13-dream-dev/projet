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
import projet.data.Coureur;
import projet.data.Materiel;

public class DaoMateriel {

	// Champs

	@Inject
	private DataSource dataSource;

	// Actions

	public int inserer(Materiel materiel) {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO materiel (  nom, lieu, date, nombredistribue) VALUES( ?, ?, ?) ";
			stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setObject(1, materiel.getNom());
			stmt.setObject(2, materiel.getNombre());
			stmt.setObject(3, materiel.getNombreDistribue());
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD
			rs = stmt.getGeneratedKeys();
			rs.next();
			materiel.setId(rs.getObject(1, Integer.class));
			return materiel.getId();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}

	public void modifier(Materiel materiel) {

		Connection cn = null;
		PreparedStatement stmt = null;
		String sql;

		try {
			cn = dataSource.getConnection();
			sql = "UPDATE materiel SET nom = ?, nombre = ?, nombredistribue = ? WHERE idmateriel =  ?";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, materiel.getNom());
			stmt.setObject(2, materiel.getNombre());
			stmt.setObject(3, materiel.getNombreDistribue());
			stmt.setObject(4, materiel.getId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}
	}

	public void supprimer(int idMateriel) {

		Connection cn = null;
		PreparedStatement stmt = null;
		String sql;

		try {
			cn = dataSource.getConnection();
			sql = "DELETE FROM materiel WHERE idmateriel = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, idMateriel);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}
	}

	public Materiel retrouver(int idMateriel) {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM materiel WHERE idmateriel = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setInt(1, idMateriel);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return construireMateriel(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}

	public List<Materiel> listerRecherche(String txt) {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM materiel WHERE nom LIKE ?  ORDER BY nom, nombre";
			stmt = cn.prepareStatement(sql);
			stmt.setString(1, '%' + txt + '%');
			rs = stmt.executeQuery();

			List<Materiel> materiel = new LinkedList<>();
			while (rs.next()) {
				materiel.add(construireMateriel(rs));
			}
			return materiel;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}

	public List<Materiel> listerTout() {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT * FROM materiel ORDER BY nom, nombre";
			stmt = cn.prepareStatement(sql);
			rs = stmt.executeQuery();

			List<Materiel> materiels = new LinkedList<>();
			while (rs.next()) {
				materiels.add(construireMateriel(rs));
			}
			return materiels;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}

	public List<Materiel> listerPourCoureur(Coureur coureur) {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT idmateriel FROM distribuer WHERE idcoureur = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, coureur.getId());
			rs = stmt.executeQuery();

			List<Materiel> materiel = new LinkedList<>();
			while (rs.next()) {
				materiel.add(retrouver(rs.getInt("idmateriel")));
			}
			return materiel;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}

	public boolean estDejaDistribue(Materiel materiel, Coureur coureur) {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT count(idcoureur) AS n FROM distribuer WHERE idmateriel = ? AND idcoureur = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, materiel.getId());
			stmt.setObject(2, coureur.getId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("n") > 0)
					return true;
			}
			return false;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}

	public void DistribuerMaterielAuCoureur(Materiel materiel, Coureur coureur) {

		Connection cn = null;
		PreparedStatement stmt = null;
		String sqlAttribuer;

		try {

			cn = dataSource.getConnection();
			sqlAttribuer = "INSERT INTO distribuer (idmateriel, idcoureur) VALUES(?,?)";
			stmt = cn.prepareStatement(sqlAttribuer);
			stmt.setObject(1, materiel.getId());
			stmt.setObject(2, coureur.getId());
			stmt.executeUpdate();

			materiel.setNombreDistribue(materiel.getNombreDistribue() + 1);
			modifier(materiel);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}
	}

	public int nombreMaterielPour(String club) {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();
			sql = "SELECT count(d.idcoureur) as n FROM distribuer d, coureur c WHERE d.idcoureur = c.idcoureur AND c.club = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setString(1, club);
			rs = stmt.executeQuery();
			if (rs.next()) {
				int n = rs.getInt("n");
				return n;
			}
			return 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}

	// Méthodes auxiliaires

	private Materiel construireMateriel(ResultSet rs) throws SQLException {
		Materiel materiel = new Materiel();
		materiel.setId(rs.getObject("idmateriel", Integer.class));
		materiel.setNom(rs.getObject("nom", String.class));
		materiel.setNombre(rs.getObject("nombre", Integer.class));
		materiel.setNombreDistribue(rs.getObject("nombredistribue", Integer.class));
		return materiel;
	}

}
