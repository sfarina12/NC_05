package Control;

import Bean.UtenteBean;
import Model.UtenteModelDm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet che implementa la login e la registrazione di un utente.
 *
 * @author Farina Simone
 */
@WebServlet("/UtenteControl")
public class UtenteControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  static UtenteModelDm model;
  static HttpSession session;
       
  /**
   * Costruttore vuoto.
   *
   * @see HttpServlet#HttpServlet()
   */
  public UtenteControl() {
    super(); 
    model =  new UtenteModelDm();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * parametri necessari per il login:
   *   -usrMail (contiene la mail dell'utente);
   *   -usrPass (contiene la password dell'utente);
   *   -usrNick (contiene il nickname dell'utente);
   *   -registerOrNot (può assumere due valori: Y o N. 
   *     indica se si deve effettuare una registrazione);
   *   -logout (può assumere due valori: Y o N. indica se si deve effettuare un' logout).
   * parametri restituiti:
   *   -role (può assumere 3 valori: -1, normal e admin.
   *     Indica se l'utente è un admin oppure un utente normale).
   */
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
  
    String username = (String) request.getAttribute("usrMail");
    String password = (String) request.getAttribute("usrPass");
    String isRegister = (String) request.getAttribute("registerOrNot");
    String logout = (String) request.getAttribute("logout");

    if (logout.charAt(0) == 'Y') {
      request.getSession().removeAttribute("role");
      request.getSession().removeAttribute("loggedUser");
      request.getSession().invalidate();

      String redirectedPage = "/HomePage.jsp";
      response.sendRedirect(request.getContextPath() + redirectedPage);
      
      return;
    }
    session = request.getSession();
    String redirectedPage = "/Login.jsp";
    try {
      if (isRegister.charAt(0) == 'N') {
        int valueCheck = checkLogin(username, password);
        switch (valueCheck) {
          case 0:
            request.getSession().setAttribute("role", "normal"); 
            redirectedPage = "/HomePage.jsp";
            break;
          case 1:
            request.getSession().setAttribute("role", "admin"); 
            redirectedPage = "/ShopPage.jsp";
            break;
          case -1:
            session.setAttribute("loggedUser", null);
            session.setAttribute("logout", "N");
            session.setAttribute("role", "-1");
            redirectedPage = "/Login.jsp";
            break;
          default:break;
        }
      } else {
        if (checkLogin(username, password) == -1) {
          UtenteBean user = new UtenteBean();
          
          user.setNickname((String) request.getAttribute("usrNick"));
          user.setMail((String) request.getAttribute("usrMail"));
          user.setPassword((String) request.getAttribute("usrPass"));
          user.setAdmin(false);

          model.doSave(user);

          redirectedPage = "/ShopPage.jsp";
        }
      }
    } catch (Exception e) {
      session.setAttribute("loggedUser", null);
      session.setAttribute("logout", "N");
      session.setAttribute("role", "-1");
      redirectedPage = "/Login.jsp";
    }
    response.sendRedirect(request.getContextPath() + redirectedPage);
  }
  
  /**
   * controlla se l'utente con quella mail e password esiste gia
   * e restituisce il ruolo dell'utente (admin o no).
   */
  private int checkLogin(String mail, String password) throws Exception {
    
    UtenteBean usr = (UtenteBean) model.doCheckLogin(mail, password);
    System.out.println(usr.getMail());
    try {
      if (usr != null) {
        session.setAttribute("loggedUser", usr);

        if (usr.getMail() == null) {
          return -1;
        }
        
        if (usr.isAdmin() == false) {
          return 0;
        }
        if (usr.isAdmin() == true) {
          return 1;
        }
      } else {
        throw new Exception("Invalid login and password");
      }
    } catch (Exception e) {
      System.out.println("Errore utente");
    }    
    return -1;
  }
}package Model;

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
  * @param photo ï¿½ la locazione della foto
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