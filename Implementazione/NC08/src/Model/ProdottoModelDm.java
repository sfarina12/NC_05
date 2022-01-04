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
  
  private final static String TABLE_NAME = "prodotto";
  private  PreparedStatement preparedStatement;
  

  public ProdottoModelDm() {}
  
  public ProdottoModelDm(ConnectionSingleton singleton) {}
  
  /**
   * Query che effettua una ricerca di tutti i prodotti presenti nel database.
   * 
   * @param preparedStatement <b>query SQL</b>
   * @return una lista di prodotti
   * @throws SQLException eccezione 
   */
  public synchronized  Collection<ProdottoBean> doRetrieveAll()
      throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String querySql = "SELECT * FROM " + TABLE_NAME;
    Collection<ProdottoBean> products = new LinkedList<ProdottoBean>();
    
    try {
      connection = new ConnectionSingleton().getInstance().getConnessione();
      preparedStatement = connection.prepareStatement(querySql);

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
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
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

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String querySql = "SELECT * FROM " + ProdottoModelDm.TABLE_NAME + " WHERE ISBN = ?";
    
    ProdottoBean bean = new ProdottoBean();
    
    try {
      connection = new ConnectionSingleton().getInstance().getConnessione();
      preparedStatement = connection.prepareStatement(querySql);
      preparedStatement.setString(1, isbn);
      ResultSet rs = preparedStatement.executeQuery();

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
  * Il metodo permette di cancellare un libro dal DB.
  *
  * @param isbn ISBN identificativo del libro
  * @return True o False
  */
  public synchronized boolean doDelete(
      String isbn) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String querySql = "DELETE FROM " + ProdottoModelDm.TABLE_NAME + " WHERE ISBN = ?";
    
    int result;
    
    try {
      connection = new ConnectionSingleton().getInstance().getConnessione();
      preparedStatement = connection.prepareStatement(querySql);
      preparedStatement.setString(1, isbn);
 
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
  * Il metodo permette di caricare la copertina relativa a un libro nel database.
  *
  * @param isbn isbn del libro al quale associare la copertina.
  * @param photo � la locazione della foto
  * @return 
  * @throws IOException, SQLException eccezzione 
  */
  // String photo = "\\WebContent\\WEB-INF\\copertine\\ + nome.jpg"
  public synchronized int updateCopertina(String isbn, String photo) 
        throws SQLException, IOException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String querySql = "UPDATE PRODOTTO SET COPERTINA = ? WHERE ISBN = ?"; 
    
    File file = new File(photo);
    
    try {
      connection = new ConnectionSingleton().getInstance().getConnessione();
      preparedStatement = connection.prepareStatement(querySql);
      FileInputStream fis = new FileInputStream(file);
      preparedStatement.setBinaryStream(1, fis, fis.available());
      preparedStatement.setString(2, isbn);
      preparedStatement.executeUpdate();
      connection.commit();
      return 1;  
    }
    finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}