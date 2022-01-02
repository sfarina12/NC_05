package Model;

import com.mysql.cj.jdbc.Blob;

import Bean.ProdottoBean;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Classe Model che gestisce le interazioni con il DataBase per la tabella Prodotto.
 *
 *@author Simone Farina
 */
public class ProdottoModelDm {
  public  static final String doRetrieveAll = "doRetrieveAll";
  public  static final String doSave = "doSave";
  public  static final String doRetrieveByKey = "doRetrieveByKey";
  public  static final String doDelete = "doDelete";
  public  static final String updateCopertina = "updateCopertina";
  
  private final static String TABLE_NAME = "prodotto";
  private  PreparedStatement preparedStatement;
  

  public ProdottoModelDm() {}
  
  public ProdottoModelDm(ConnectionSingleton singleton) {}


  /**
   * Metodo per effettuare query.
   *
   * @param methodName nome della <b>query</b>
   * @param parameter  <b>parametro</b> passato alla query
   * @return un <b>object</b>
   * @throws SQLException
   */
  public  synchronized Object doQuery(String methodName, Object parameter)
      throws SQLException {

    Connection connection = null;
    preparedStatement = null;
    String querySql;

    try {

      new ConnectionSingleton();
      connection = ConnectionSingleton.getInstance().getConnessione();

      switch (methodName) {

        case doRetrieveAll:
          querySql = "SELECT * FROM " + TABLE_NAME;
          preparedStatement = connection.prepareStatement(querySql);
          return doRetrieveAll();

        case doSave:
          querySql = "INSERT INTO " + ProdottoModelDm.TABLE_NAME
            + " (ISBN, TITOLO, AUTORE, PREZZO, COPERTINA, DESCRIZIONE,"
            + " NOME_CATEGORIA, QUANTITA_STOCK) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
          preparedStatement = connection.prepareStatement(querySql);
          return doSave((ProdottoBean) parameter);

        case doRetrieveByKey:
          querySql = "SELECT * FROM " + ProdottoModelDm.TABLE_NAME + " WHERE isbn = ?";
          preparedStatement = connection.prepareStatement(querySql);
          return doRetrieveByKey((String) parameter);
         
        case doDelete:
          querySql = "DELETE FROM " + ProdottoModelDm.TABLE_NAME + " WHERE ISBN = ?";
          preparedStatement = connection.prepareStatement(querySql);
          return doDelete((String) parameter);
        default:
          return null;

      }
      /*
    } finally {

      try {
        if (preparedStatement != null) {
          preparedStatement.close();
          System.out.println("bla bla");
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
      String methodName, Object parameter1, Object parameter2)
      throws SQLException, IOException {
    Connection connection = null;
    preparedStatement = null;
    String querySql;
    try {
      new ConnectionSingleton();
      connection = ConnectionSingleton.getInstance().getConnessione();
      querySql = "UPDATE PRODOTTO SET COPERTINA = ? WHERE ISBN = ?";
      preparedStatement = connection.prepareStatement(querySql);
      return updateCopertina(connection, (String) parameter1, (String) parameter2);
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
   * Query che effettua una ricerca di tutti i prodotti presenti nel database.
   * 
   * @param preparedStatement <b>query SQL</b>
   * @return una lista di prodotti
   * @throws SQLException eccezione 
   */
  public synchronized  Collection<ProdottoBean> doRetrieveAll()
      throws SQLException {

    Collection<ProdottoBean> products = new LinkedList<ProdottoBean>();
    //-------------------------------------------------------------------------
    Connection connection = null;
    preparedStatement = null;

    new ConnectionSingleton();
    connection = ConnectionSingleton.getInstance().getConnessione();

    String querySql = "SELECT * FROM " + TABLE_NAME;
    preparedStatement = connection.prepareStatement(querySql);
    //-------------------------------------------------------------------------

    ResultSet rs = preparedStatement.executeQuery();

    while (rs.next()) {
      ProdottoBean bean = new ProdottoBean();

      bean.setIsbn(rs.getString("ISBN"));
      bean.setTitolo(rs.getString("TITOLO"));
      bean.setAutore(rs.getString("AUTORE"));
      bean.setPrezzo(rs.getFloat("PREZZO"));
      Blob imgBlob = (Blob) rs.getBlob("COPERTINA");
      if (imgBlob != null) {
        bean.setCopertina(imgBlob.getBytes(1, (int) imgBlob.length()));
      }
      bean.setDescrizione(rs.getString("DESCRIZIONE"));
      bean.setNomeCategoria(rs.getString("NOME_CATEGORIA")); 
      bean.setQuantitaStock(rs.getInt("QUANTITA_STOCK"));
      products.add(bean);
    }

    return products;
  }

  /**
   * Il metodo permette di salvare un prodotto sul database.
   *
   * @param product prodotto da salvare all'interno del database.
   * @return 
   */
  public synchronized  int doSave(ProdottoBean product)
      throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "INSERT INTO " + ProdottoModelDm.TABLE_NAME
        + " (ISBN, TITOLO, AUTORE, PREZZO, COPERTINA, DESCRIZIONE, NOME_CATEGORIA, QUANTITA_STOCK) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try {
      new ConnectionSingleton();
	connection = ConnectionSingleton.getInstance().getConnessione();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, product.getIsbn());
      preparedStatement.setString(2, product.getTitolo());
      preparedStatement.setString(3, product.getAutore());
      preparedStatement.setFloat(4, product.getPrezzo());
      InputStream dc = new ByteArrayInputStream(product.getCopertina());
      preparedStatement.setBinaryStream(5, dc);
      preparedStatement.setString(6, product.getDescrizione());
      preparedStatement.setString(7, product.getNomeCategoria());
      preparedStatement.setInt(8, product.getQuantitaStock());
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
  * Il metodo permette di selezionare un prodotto dal DB in base al suo codice isbn.
  *
  * @param isbn ISBN identificativo del libro.
  * @return ProdottoBean
  */
  public synchronized ProdottoBean doRetrieveByKey(
      String isbn) throws SQLException {

    //-------------------------------------------------------------------------
    Connection connection = null;
    preparedStatement = null;

    new ConnectionSingleton();
    connection = ConnectionSingleton.getInstance().getConnessione();

    String querySql = "SELECT FROM " + ProdottoModelDm.TABLE_NAME + " WHERE ISBN = ?";
    preparedStatement = connection.prepareStatement(querySql);
     //-------------------------------------------------------------------------

    preparedStatement.setString(1, isbn);

    ResultSet rs = preparedStatement.executeQuery();
    ProdottoBean bean = new ProdottoBean();
    while (rs.next()) {
      bean.setIsbn(rs.getString("ISBN"));
      bean.setTitolo(rs.getString("TITOLO"));
      bean.setAutore(rs.getString("AUTORE"));
      bean.setPrezzo(rs.getFloat("PREZZO"));
      Blob imgBlob = (Blob) rs.getBlob("COPERTINA");
      bean.setCopertina(imgBlob.getBytes(1, (int) imgBlob.length()));
      bean.setDescrizione(rs.getString("DESCRIZIONE"));
      bean.setNomeCategoria(rs.getString("NOME_CATEGORIA"));
      bean.setQuantitaStock(rs.getInt("QUANTITA_STOCK"));
    }
    return bean;
  }

  /**
  * Il metodo permette di cancellare un libro dal DB.
  *
  * @param isbn ISBN identificativo del libro
  * @return True o False
  */
  public synchronized boolean doDelete(
      String isbn) throws SQLException {

    //-------------------------------------------------------------------------
    Connection connection = null;
    preparedStatement = null;

    new ConnectionSingleton();
    connection = ConnectionSingleton.getInstance().getConnessione();

    String querySql = "DELETE FROM " + ProdottoModelDm.TABLE_NAME + " WHERE ISBN = ?";
    preparedStatement = connection.prepareStatement(querySql);
     //-------------------------------------------------------------------------
     preparedStatement.setString(1, isbn);
 
    return (preparedStatement.executeUpdate() != 0);
  }

  /**
  * Il metodo permette di caricare la copertina relativa a un libro nel database.
  *
  * @param isbn isbn del libro al quale associare la copertina.
  * @param photo � la locazione della foto
  * @return 
  * @throws IOException, SQLException eccezzione 
  */
  public synchronized int updateCopertina(
      Connection con, String isbn, String photo) throws SQLException, IOException {

    File file = new File(photo);
    try {
      FileInputStream fis = new FileInputStream(file);
      preparedStatement.setBinaryStream(1, fis, fis.available());
      preparedStatement.setString(2, isbn);
      
      preparedStatement.executeUpdate();
      con.commit();
      return 1;  
    } catch (SQLException sqlException) {
      System.out.println(sqlException);
    }
    return 0;
  }
  
private static void jout(Object obj) {System.out.println(obj);}
}

