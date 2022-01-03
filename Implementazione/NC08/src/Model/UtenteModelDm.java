package Model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.UtenteBean;

/**
 * Classe Model che gestisce le interazioni con il DataBase per la tabella Utente.
 *
 *@author Farina Simone
 */
public class UtenteModelDm {

  private static final String TABLE_NAME = "utente";
  private  PreparedStatement preparedStatement;


  /**
   * Metodo che permette di cancellare un utente dal sito.
   *  
   * @param email <b>parametro</b> passato alla query
   * @return un <b>boolean</b> true in caso di riuscita
   * @throws SQLException 
   */
  public synchronized  boolean doDelete(String email)
      throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int result = 0;
    String querySql = "DELETE FROM " + UtenteModelDm.TABLE_NAME + " WHERE MAIL = ?";

    try {
      connection = new ConnectionSingleton().getInstance().getConnessione();
      preparedStatement = connection.prepareStatement(querySql);
      preparedStatement.setString(1, email);
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

/**
 * 
 * @param preparedStatement
 * @param bean
 * @return
 * @throws SQLException
 */
  public synchronized int doSave(UtenteBean bean)
      throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String querySql = "INSERT INTO " + UtenteModelDm.TABLE_NAME
            + " (MAIL, PASSWORD, NICKNAME, IS_ADMIN) VALUES"
            + " (?, ?, ?, ?)";

    try {
      connection = new ConnectionSingleton().getInstance().getConnessione();
      preparedStatement = connection.prepareStatement(querySql);
      preparedStatement.setString(1, bean.getMail());
      preparedStatement.setString(2, bean.getPassword());
      preparedStatement.setString(3, bean.getNickname());
      int role =  bean.isAdmin() ? 1 : 0; //ottiene versone in int del boolean
      preparedStatement.setInt(4, role);
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
   * Medoto che restituisce i dati di un utente avente una certa mail in input.
   *
   * @param mail mail dell'utente
   */ 
  public synchronized UtenteBean doRetrieveByKey(String mail)
      throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String querySql = "SELECT * FROM " + UtenteModelDm.TABLE_NAME 
            + " WHERE MAIL= ?";


    UtenteBean bean = new UtenteBean();

    try {
      connection = new ConnectionSingleton().getInstance().getConnessione();
      preparedStatement = connection.prepareStatement(querySql);
      preparedStatement.setString(1, mail);
      ResultSet rs = preparedStatement.executeQuery();     

      while (rs.next()) {
        bean.setMail(rs.getString("MAIL"));
        bean.setPassword(rs.getString("PASSWORD"));
        bean.setNickname(rs.getString("NICKNAME"));
        bean.setAdmin((rs.getInt("IS_ADMIN")) == 1);
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
   * Medoto che controlla se esistono utenti con quella password o mail.
   *
   */
  public synchronized UtenteBean doCheckLogin(
      String email, String password) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String querySql = "SELECT * FROM " + UtenteModelDm.TABLE_NAME 
           + " WHERE MAIL= ? AND PASSWORD= ?";

    UtenteBean bean = new UtenteBean();

    try {
      connection = new ConnectionSingleton().getInstance().getConnessione();
      preparedStatement = connection.prepareStatement(querySql);
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, password);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setNickname(rs.getString("NICKNAME"));
        bean.setMail(rs.getString("MAIL"));
        bean.setPassword(rs.getString("PASSWORD"));
        bean.setAdmin(rs.getInt("IS_ADMIN") == 1); 
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
}
   