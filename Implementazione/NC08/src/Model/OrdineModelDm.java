package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Bean.OrdineBean;

/**
 * Classe Model che gestisce le interazioni con il DataBase per la tabella Ordine.
 *
 *@author Farina_Simone
 */
public class OrdineModelDm {
  private static final String TABLE_NAME = "ordine";

  /**
   * Quando invocato, salverà l'ordine all'interno del database.
   *
   * @param bean - l'ordine da salvare
   */
  public synchronized int doSave(OrdineBean bean) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "INSERT INTO " + OrdineModelDm.TABLE_NAME
          + " (DATA, TOTALE, METODO_PAGAMENTO, INDIRIZZO, UTENTE_MAIL)"
          + " VALUES (?, ?, ?, ?, ?)";

    try {
      connection = new ConnectionSingleton().getInstance().getConnessione();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, bean.getData());
      preparedStatement.setFloat(2, bean.getTotale());
      preparedStatement.setInt(3, bean.isMetodoPagamento() ? 1 : 0);
      preparedStatement.setString(4, bean.getIndirizzo());
      preparedStatement.setString(5, bean.getMail());
      preparedStatement.executeUpdate();

      connection.commit();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
        return 0;
      }
    }
    
    return 1;
  }
  
  /**
   * Quando invocato, recupererà uno specifico ordine dal database.
   *
   * @param id - id dell'ordine da recuperare
   */
  public synchronized OrdineBean doRetrieveByKey(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    OrdineBean bean = new OrdineBean();
    String selectSql = "SELECT * FROM " + OrdineModelDm.TABLE_NAME + " WHERE ID_ORDINE = ?";

    try {
      connection = new ConnectionSingleton().getInstance().getConnessione();
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1, id);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setIdOrdine(rs.getInt("ID_ORDINE"));
        bean.setData(rs.getString("DATA"));
        bean.setTotale(rs.getFloat("TOTALE"));
        bean.setMetodoPagamento(rs.getInt("METODO_PAGAMENTO") == 0 ? false : true);
        bean.setIndirizzo(rs.getString("INDIRIZZO"));
        bean.setMail(rs.getString("UTENTE_MAIL"));
      }

    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return bean;
  }
  
  /**
   * Quando invocato, recupererà tutti gli ordini associato ad un untente, dal database.
   *
   * @param mail - la mail dell'utente
   */
  public synchronized ArrayList<OrdineBean> doRetrieveAll(String mail) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ArrayList<OrdineBean> ordini = new ArrayList<OrdineBean>();
    
    OrdineBean bean = new OrdineBean();
    String selectSql = "SELECT * FROM " + OrdineModelDm.TABLE_NAME + " WHERE UTENTE_MAIL = ?";

    try {
      connection = new ConnectionSingleton().getInstance().getConnessione();
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, mail);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setIdOrdine(rs.getInt("ID_ORDINE"));
        bean.setData(rs.getString("DATA"));
        bean.setTotale(rs.getFloat("TOTALE"));
        bean.setMetodoPagamento(rs.getInt("METODO_PAGAMENTO") == 0 ? false : true);
        bean.setIndirizzo(rs.getString("INDIRIZZO"));
        bean.setMail(rs.getString("UTENTE_MAIL"));
        
        ordini.add(bean);
      }

    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return ordini;
  }
  
  /**
   * Quando invocato, rimuoverà un determinato ordine dal database.
   *
   * @param id - l'id dell'ordine da eliminare
   */
  public synchronized boolean doDelete(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSql = "DELETE FROM " + OrdineModelDm.TABLE_NAME + " WHERE ID_ORDINE = ?";

    int result = 0;
    
    try {
      connection = new ConnectionSingleton().getInstance().getConnessione();
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setInt(1, id);
      result = preparedStatement.executeUpdate();
      connection.commit();
        
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return (result != 0);
  }
}
