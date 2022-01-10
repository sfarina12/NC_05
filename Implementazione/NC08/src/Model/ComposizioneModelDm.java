package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Bean.ComposizioneBean;

/**
 * consente di prendere informazioni dal database riguardo gli oridni.
 *
 * @author Farina_Simone
 */
public class ComposizioneModelDm {
  private static final String TABLE_NAME = "composizione";

  /**
   * Quando invocato, salverà la composizione all'interno del database.
   *
   * @param bean - l'ordine da salvare
   */
  public synchronized int doSave(ComposizioneBean bean) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "INSERT INTO " + ComposizioneModelDm.TABLE_NAME
          + " (ORDINE_ID_ORDINE, PRODOTTO_ISBN, QUANTITA)"
          + " VALUES (?, ?, ?)";

    try {
      connection = new ConnectionSingleton().getInstance().getConnessione();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setInt(1, bean.getIdOrdine());
      preparedStatement.setString(2, bean.getIsbn());
      preparedStatement.setInt(3, bean.getQuantita());
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
   * Quando invocato, recupererà una specifica composizione legata ad un ordine dal database.
   *
   * @param idOrdine - id dell'ordine da recuperare
   */
  public synchronized ComposizioneBean doRetrieveByKey(int idOrdine) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ComposizioneBean bean = new ComposizioneBean();
    String selectSql = "SELECT * FROM " + ComposizioneModelDm.TABLE_NAME 
        + " WHERE ORDINE_ID_ORDINE = ?";

    try {
      connection = new ConnectionSingleton().getInstance().getConnessione();
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1, idOrdine);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setIdOrdine(rs.getInt("ORDINE_ID_ORDINE"));
        bean.setIsbn(rs.getString("PRODOTTO_ISBN"));
        bean.setQuantita(rs.getInt("QUANTITA"));
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
   * Quando invocato, rimuoverà ua determinata composizione dal database.
   *
   * @param idOrdine - l'id dell'ordine da eliminare
   */
  public synchronized boolean doDelete(int idOrdine) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSql = "DELETE FROM " + ComposizioneModelDm.TABLE_NAME
        + " WHERE ORDINE_ID_ORDINE = ?";

    int result = 0;
    
    try {
      connection = new ConnectionSingleton().getInstance().getConnessione();
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setInt(1, idOrdine);
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
