/*
 * package projet.dao;
 * 
 * import java.sql.Connection; import java.sql.PreparedStatement; import
 * java.sql.ResultSet; import java.sql.SQLException; import java.sql.Statement;
 * import java.util.ArrayList; import java.util.List;
 * 
 * import javax.inject.Inject; import javax.sql.DataSource;
 * 
 * import jfox.dao.jdbc.UtilJdbc; import projet.data.Benevole; import
 * projet.data.Poste;
 * 
 * 
 * public class DaoPoste {
 * 
 * 
 * // Champs
 * 
 * @Inject private DataSource dataSource;
 * 
 * 
 * // Actions
 * 
 * public void insererPourBenevole( Benevole benevole ) {
 * 
 * Connection cn = null; PreparedStatement stmt = null; ResultSet rs = null;
 * String sql;
 * 
 * try { cn = dataSource.getConnection(); sql =
 * "INSERT INTO poste ( idbenevole, libelle, numero, heured, etat) VALUES (?,?,?,?,?)"
 * ; stmt = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS ); for(
 * Poste poste : benevole.getPostes() ) { stmt.setObject( 1, benevole.getId() );
 * stmt.setObject( 2, poste.getLibelle() ); stmt.setObject( 3,
 * poste.getNombrePlaces() ); stmt.setObject( 4, poste.getHeureD() );
 * stmt.setObject( 5, poste.getEtat() ); stmt.executeUpdate();
 * 
 * // Récupère l'identifiant généré par le SGBD rs = stmt.getGeneratedKeys();
 * rs.next(); poste.setId( rs.getObject( 1, Integer.class) ); } } catch
 * (SQLException e) { throw new RuntimeException(e); } finally { UtilJdbc.close(
 * stmt, cn ); } }
 * 
 * 
 * public void modifierPourBenevole( Benevole benevole ) {
 * 
 * Connection cn = null; PreparedStatement stmtDelete = null; PreparedStatement
 * stmtInsert = null; PreparedStatement stmtUpdate = null; ResultSet rs = null;
 * String sql;
 * 
 * try { cn = dataSource.getConnection();
 * 
 * sql = "DELETE FROM poste WHERE idposte = ?"; stmtDelete =
 * cn.prepareStatement( sql ); for ( Poste poste : listerPourBenevole(benevole)
 * ) { if ( ! benevole.getPostes().contains( poste ) ) { stmtDelete.setObject(
 * 1, poste.getId() ); stmtDelete.executeUpdate(); } }
 * 
 * sql =
 * "INSERT INTO poste ( idbenevole, libelle, nombreplace, heured, etat ) VALUES (?,?,?,?,?)"
 * ; stmtInsert = cn.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
 * sql =
 * "UPDATE poste SET idbenevole = ?, libelle = ?, nombreplace = ?, heureD = ?, etat = ? WHERE idposte = ?"
 * ; stmtUpdate = cn.prepareStatement( sql ); for( Poste poste :
 * benevole.getPostes() ) { if ( poste.getId() == null || poste.getId() == 0 ) {
 * stmtInsert.setObject( 1, benevole.getId()); stmtInsert.setObject( 2,
 * poste.getLibelle() ); stmtInsert.setObject( 3, poste.getNombrePlaces() );
 * stmtInsert.setObject( 4, poste.getHeureD() ); stmtInsert.setObject( 5,
 * poste.getEtat() ); stmtInsert.executeUpdate(); // Récupère l'identifiant
 * généré par le SGBD rs = stmtInsert.getGeneratedKeys(); rs.next();
 * poste.setId( rs.getObject( 1, Integer.class) ); } else {
 * stmtUpdate.setObject( 1, benevole.getId()); stmtUpdate.setObject( 2,
 * poste.getLibelle() ); stmtInsert.setObject( 3, poste.getNombrePlaces() );
 * stmtInsert.setObject( 4, poste.getHeureD() ); stmtInsert.setObject( 5,
 * poste.getEtat() ); stmtUpdate.setObject( 6, poste.getId());
 * stmtUpdate.executeUpdate(); } } } catch (SQLException e) { throw new
 * RuntimeException(e); } finally { UtilJdbc.close( stmtDelete, stmtInsert,
 * stmtUpdate, cn ); } }
 * 
 * 
 * public void supprimerPourBenevole( int idBenevole ) {
 * 
 * Connection cn = null; PreparedStatement stmt = null; String sql;
 * 
 * try { cn = dataSource.getConnection();
 * 
 * // Supprime les postes sql = "DELETE FROM poste  WHERE idbenevole = ? "; stmt
 * = cn.prepareStatement(sql); stmt.setObject( 1, idBenevole );
 * stmt.executeUpdate();
 * 
 * } catch (SQLException e) { throw new RuntimeException(e); } finally {
 * UtilJdbc.close( stmt, cn ); } }
 * 
 * 
 * public List<Poste> listerPourBenevole( Benevole benevole ) {
 * 
 * Connection cn = null; PreparedStatement stmt = null; ResultSet rs = null;
 * String sql;
 * 
 * try { cn = dataSource.getConnection();
 * 
 * sql = "SELECT * FROM poste WHERE idbenevole = ? ORDER BY libelle"; stmt =
 * cn.prepareStatement(sql); stmt.setObject( 1, benevole.getId() ); rs =
 * stmt.executeQuery();
 * 
 * List<Poste> postes = new ArrayList<>(); while (rs.next()) { postes.add(
 * construireposte( rs, benevole ) ); } return postes;
 * 
 * } catch (SQLException e) { throw new RuntimeException(e); } finally {
 * UtilJdbc.close( rs, stmt, cn ); } }
 * 
 * 
 * // Méthodes auxiliaires
 * 
 * private Poste construireposte( ResultSet rs, Benevole benevole ) throws
 * SQLException { Poste poste = new Poste(); poste.setId(rs.getObject(
 * "idposte", Integer.class )); poste.setLibelle(rs.getObject( "libelle",
 * String.class )); poste.setNombrePlaces(rs.getObject( "nombreplace",
 * Integer.class )); poste.setHeureD(rs.getObject( "heureD", String.class ));
 * poste.setEtat(rs.getObject( "etat", String.class )); return poste; }
 * 
 * }
 */