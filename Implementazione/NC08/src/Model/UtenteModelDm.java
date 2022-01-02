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

  private static final String doSave = "doSave";
  private static final String doRetrieveByKey = "doRetrieveByKey";
  private static final String doDelete = "doDelete";
  private static final String doCheckLogin = "doCheckLogin";
  private static final String TABLE_NAME = "utente";
  private  PreparedStatement preparedStatement;

  /**
   * Metodo per effettuare query.
   *
   * @param methodName nome della <b>query</b>
   * @param parameter  <b>parametro</b> passato alla query
   * @return un <b>object</b>
   * @throws SQLException eccezione
   */
  public synchronized Object doQuery(String methodName, Object parameter)
      throws SQLException {
    Connection connection = null;
    preparedStatement = null;
    String querySql;

    try {

      new ConnectionSingleton();
      connection = ConnectionSingleton.getInstance().getConnessione();

      switch (methodName) {

        case doSave:
          querySql = "INSERT INTO " + UtenteModelDm.TABLE_NAME
            + " (MAIL, PASSWORD, NICKNAME, IS_ADMIN) VALUES"
            + " (?, ?, ?, ?)";
          preparedStatement = connection.prepareStatement(querySql);
          return doSave((UtenteBean) parameter);

        case doRetrieveByKey:
          querySql = "SELECT * FROM " + UtenteModelDm.TABLE_NAME + " WHERE MAIL = ?";
          preparedStatement = connection.prepareStatement(querySql);
          return doRetrieveByKey((String) parameter);

        case doDelete:
          querySql = "DELETE FROM " + UtenteModelDm.TABLE_NAME + " WHERE MAIL = ?";
          preparedStatement = connection.prepareStatement(querySql);
          return doDelete((String) parameter);
          
        default:
          return null;
      }
      
      /*} finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }*/
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
  //}

  /**
   * Metodo per effettuare query per le immagini.
   *
   * @param methodName nome della <b>query</b>
   * @param parameter1  <b>parametro</b> passato alla query
   * @param parameter2 <b>parametro</b> passato alla query
   * @return un <b>object</b>
   * @throws SQLException
   */
  public synchronized Object doQuery(
      Connection conn, Object parameter1, Object parameter2)
      throws SQLException, IOException {
    Connection connection = null;
    preparedStatement = null;
    String querySql;
    try {
      new ConnectionSingleton();
      connection = ConnectionSingleton.getInstance().getConnessione();
      querySql = "INSERT INTO " + UtenteModelDm.TABLE_NAME
            + " (MAIL, PASSWORD, NICKNAME, IS_ADMIN) VALUES" 
            + " (?, ?, ?, ?)";
      preparedStatement = connection.prepareStatement(querySql);
      return doCheckLogin(connection, (String) parameter1, (String) parameter2);
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
  /**
   * Metodo che permette di cancellare un utente dal sito.
   *  
   * @param email <b>parametro</b> passato alla query
   * @return un <b>boolean</b> true in caso di riuscita
   * @throws SQLException 
   */
  public synchronized  boolean doDelete(String email)
      throws SQLException {

    //-------------------------------------------------------------------------
    Connection connection = null;
    preparedStatement = null;

    new ConnectionSingleton();
    connection = ConnectionSingleton.getInstance().getConnessione();

    String querySql = "DELETE FROM " + UtenteModelDm.TABLE_NAME + " WHERE MAIL = ?";
    preparedStatement = connection.prepareStatement(querySql);
    //-------------------------------------------------------------------------

    preparedStatement.setString(1, email);
    return (preparedStatement.executeUpdate() != 0);
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

    //-------------------------------------------------------------------------
    Connection connection = null;
    preparedStatement = null;

    new ConnectionSingleton();
    connection = ConnectionSingleton.getInstance().getConnessione();

    String querySql = "INSERT INTO " + UtenteModelDm.TABLE_NAME
            + " (MAIL, PASSWORD, NICKNAME, IS_ADMIN) VALUES"
            + " (?, ?, ?, ?)";
    preparedStatement = connection.prepareStatement(querySql);
    //-------------------------------------------------------------------------

    preparedStatement.setString(1, bean.getMail());
    preparedStatement.setString(2, bean.getPassword());
    preparedStatement.setString(3, bean.getNickname());
    int role =  bean.isAdmin() ? 1 : 0; //ottiene versone in int del boolean
    preparedStatement.setInt(4, role);
    return 1;
    //return preparedStatement.executeUpdate();
  }
  
  /**
   * Medoto che restituisce i dati di un utente avente una certa mail in input.
   *
   * @param mail mail dell'utente
   */ 
  public synchronized UtenteBean doRetrieveByKey(String mail)
      throws SQLException {

    //-------------------------------------------------------------------------
    Connection connection = null;
    preparedStatement = null;

    new ConnectionSingleton();
    connection = ConnectionSingleton.getInstance().getConnessione();

    String querySql = "INSERT INTO " + UtenteModelDm.TABLE_NAME
        + " (MAIL, PASSWORD, NICKNAME, IS_ADMIN) VALUES"
        + " (?, ?, ?, ?)";
    preparedStatement = connection.prepareStatement(querySql);
    //------------------------------------------------------------------------
    preparedStatement.setString(1, mail);
    ResultSet rs = preparedStatement.executeQuery();
    UtenteBean bean = new UtenteBean();

    while (rs.next()) {
      bean.setMail(rs.getString("MAIL"));
      bean.setPassword(rs.getString("PASSWORD"));
      bean.setNickname(rs.getString("NICKNAME"));
      bean.setAdmin((rs.getInt("IS_ADMIN")) == 1);
    }
    return bean;
  }
  
  /**
   * Medoto che ceontrolla se esistono utenti con qulla password o mail.
   *
   */
  public synchronized UtenteBean doCheckLogin(Connection conn,
      String email, String password) throws SQLException {

    //-------------------------------------------------------------------------
    Connection connection = null;
    preparedStatement = null;

    new ConnectionSingleton();
    connection = ConnectionSingleton.getInstance().getConnessione();

    String querySql = "INSERT INTO " + UtenteModelDm.TABLE_NAME
        + " (MAIL, PASSWORD, NICKNAME, IS_ADMIN) VALUES"
        + " (?, ?, ?, ?)";
    preparedStatement = connection.prepareStatement(querySql);
    //------------------------------------------------------------------------
    preparedStatement.setString(1, email);
    preparedStatement.setString(2, password);

    ResultSet rs = preparedStatement.executeQuery();

    if (rs.next()) {
      UtenteBean bean = new UtenteBean();
      bean.setNickname(rs.getString("NICKNAME"));
      bean.setMail(rs.getString("MAIL"));
      bean.setPassword(rs.getString("PASSWORD"));
      bean.setAdmin(rs.getInt("IS_ADMIN") == 1);

      return bean;
    }
    return null;
  }
}
 
