import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import Bean.ProdottoBean;
import Model.ProdottoModelDm;

/**
 * Inizializza il database e lo crea nel caso non esista.
 *
 * @author Farina_Simone
 *
 */

public class Installer {

  private final static ProdottoModelDm model = new ProdottoModelDm();

  /**
   * inserire corrispettivamente l'username e la password come argomenti del main
   */  
  public static void main(String[] args) { 
    try {
      if (args.length > 0) {
        String usr = args[0];
        String pass = args[1];
    	
        install(usr, pass);
      } else {
        install("root", "admin");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  private static void install(String user, String pass) throws Exception {

    String url = "jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding="
        + "UTF-8&allowPublicKeyRetrieval=true&useSSL=false&useJDBCCompliant"
        + "TimezoneShift=true&auseLegacyDatetimeCode=false&serverTimezone=UTC";
    Class.forName("com.mysql.cj.jdbc.Driver");
    try (Connection conn = DriverManager.getConnection(url, user, pass); 
        Statement stmt = conn.createStatement();) {
      //String sql = "CREATE DATABASE dodo";
      //stmt.executeUpdate(sql);

      ScriptRunner sr = new ScriptRunner(conn, false, false);
      Reader reader = new BufferedReader(new FileReader("installer\\Inizializzazione_Dodo.sql"));
      sr.runScript(reader);
    } catch (SQLException e) {
      e.printStackTrace();
    } 
      
    List<ProdottoBean> prodotti;
    
    try {
      prodotti = (List<ProdottoBean>) model.doRetrieveAll();
      for (int i = 0; i < prodotti.size(); i++) {
        model.updateCopertina(prodotti.get(i).getIsbn(),
            ".\\WebContent\\WEB-INF\\copertine\\" + prodotti.get(i).getIsbn() + ".jpg");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
