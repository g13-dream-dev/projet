
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
import projet.data.Benevole;
import projet.data.Permis;

public class DaoPermis {

	// Champs

	@Inject
	private DataSource dataSource;

	// Actions

	public void insererPourBenevole(Benevole benevole) {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();
			sql = "INSERT INTO permis ( idpermis, numero, datedelivrance ) VALUES (?,?,?)";
			stmt = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setObject(1, benevole.getId());
			stmt.setObject(2, benevole.getPermis().getNumero());
			stmt.setObject(3, benevole.getPermis().getDateDelivrance());
			stmt.executeUpdate();

			// Récupère l'identifiant généré par le SGBD 
			rs = stmt.getGeneratedKeys();
			rs.next();
			benevole.getPermis().setId(rs.getObject(1, Integer.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}
	}

	public void modifierPourBenevole(Benevole benevole) {

		Connection cn = null;
		PreparedStatement stmtDelete = null;
		PreparedStatement stmtInsert = null;
		PreparedStatement stmtUpdate = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			sql = "DELETE FROM permis WHERE idpermis = ?";
			stmtDelete = cn.prepareStatement(sql);
			Permis p = avoirPourBenevole(benevole);
			if(p != null) {
				if (!benevole.getPermis().equals(p)) {
					stmtDelete.setObject(1, p.getId());
					stmtDelete.executeUpdate();
				}
			}
			

			sql = "INSERT INTO telephone ( idpermis, numero, datedelivrance ) VALUES (?,?,?)";
			stmtInsert = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sql = "UPDATE permis SET idpermis = ?, numero = ?, datedelivrance = ? WHERE idpermis = ?";
			stmtUpdate = cn.prepareStatement(sql);

			if (benevole.getPermis().getId() == null || benevole.getPermis().getId() == 0) {
				stmtInsert.setObject(1, benevole.getId());
				stmtInsert.setObject(2, benevole.getPermis().getNumero());
				stmtInsert.setObject(3, benevole.getPermis().getDateDelivrance());
				stmtInsert.executeUpdate();
				// Récupère l'identifiant généré par le SGBD
				rs = stmtInsert.getGeneratedKeys();
				rs.next();
				benevole.getPermis().setId(rs.getObject(1, Integer.class));
			} else {
				stmtUpdate.setObject(1, benevole.getId());
				stmtUpdate.setObject(2, benevole.getPermis().getNumero());
				stmtUpdate.setObject(3, benevole.getPermis().getDateDelivrance());
				stmtUpdate.setObject(4, benevole.getPermis().getId());
				stmtUpdate.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmtDelete, stmtInsert, stmtUpdate, cn);
		}
	}

	public void supprimerPourBenevole(int idBenevole) {

		Connection cn = null;
		PreparedStatement stmt = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			// Supprime les telephones
			sql = "DELETE FROM permis  WHERE idpermis = ? ";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, idBenevole);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(stmt, cn);
		}
	}

	public Permis avoirPourBenevole(Benevole benevole) {

		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			cn = dataSource.getConnection();

			sql = "SELECT * FROM permis WHERE idpermis = ?";
			stmt = cn.prepareStatement(sql);
			stmt.setObject(1, benevole.getId());
			rs = stmt.executeQuery();

			Permis permis = null;
			rs.next();
			permis = construirePermis(rs, benevole);
			
			return permis;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			UtilJdbc.close(rs, stmt, cn);
		}
	}

	// Méthodes auxiliaires

	private Permis construirePermis(ResultSet rs, Benevole benevole) throws SQLException {
		Permis permis = new Permis();
		permis.setId(rs.getObject("idpermis", Integer.class));
		permis.setNumero(rs.getObject("numero", String.class));
		permis.setDateDelivrance(rs.getObject("datedelivrance", LocalDate.class));
		return permis;
	}

}
