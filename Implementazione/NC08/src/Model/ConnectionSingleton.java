package Model;

import java.sql.Connection;
import java.sql.DriverManager;
/** ConnectionSingleton è la classe che permette di collegarsi al database così,
 * una volta stabilita la connessione al database si avrà la libertà di mandare query.
 * ConnectionSingleton è stato scritto in modo da poterlo usare come singleton,
 * evitando istanze multiple.
 *
 * @author Farina Simone
 *
 */

public class ConnectionSingleton {

  /**
   * Istanza del ConnectionSingleton.
   */
  private static ConnectionSingleton instance = null;
  /**
   * Connessione con il Database.
   */
  private Connection connessione;
  /**
   * Nome sotto formato stringa del database al quale la classe si connette.
   */
  private String nomeDatabase;
  /**
   * Username per l'accesso al database.
   */
  private String nomeUser;
  /**
   * Password per l'accesso al database.
   */
  private String password;
  /**
   * Porta sulla quale si affaccia il servizio del database.
   */
  private int portaHost;
  /**
   * indirizzo ip (in questo caso localhost) dal quale il database si affaccia.
   */
  private String nomeHost;

  /**
   * Costruttore del ConnectionSingleton.
   * Inizializza le variabili relative alla connessione al db e
   * poi cerca di creare la connessione e salvarla tra gli attributi.
   */
  public ConnectionSingleton() {
    this.connessione = null;
    this.nomeDatabase = "dodo";
    this.nomeUser = "root";
    this.password = "admin";
    this.portaHost = 3306;
    this.nomeHost = "localhost";

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.connessione = DriverManager.getConnection("jdbc:mysql://localhost:3306/dodo?useUnicode="
        + "true&characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useSSL="
        + "false&useJDBCCompliantTimezoneShift=true&auseLegacyDatetimeCode="
        + "false&serverTimezone=UTC", this.nomeUser, this.password);
      this.connessione.setAutoCommit(false);
    } catch (Exception exc) {
      System.out.println(exc.getMessage());
    }
  }

  /**
   * Si usa per ottenere il singleton, è il metodo chiave del design pattern scelto.
   */
  public static  ConnectionSingleton getInstance() {
    if (instance == null) {
      instance = new ConnectionSingleton();
    }
    return instance;
  }

  /**
   * Restituisce la connessione creata all'inizializzazione.
   */
  public Connection getConnessione() {
    return this.connessione;
  }

  /**
   * Serve per settare la connessione.
   *
   * @param connessione è la variabile che ti permette di collegarti al database.
   */
  public void setConnessione(Connection connessione) {
    this.connessione = connessione;
  }

  /**
   * Restituisce il nome del database, creato all'inizializzazione.
   */
  public String getNomeDatabase() {
    return this.nomeDatabase;
  }

  /**
   * Serve per settare il nome del database.
   *
   * @param nomeDatabase è la variabile che contiene il nome del database.
   */
  public void setNomeDatabase(String nomeDatabase) {
    this.nomeDatabase = nomeDatabase;
  }

  /**
   * Restituisce l'username per la connessione al database.
   */
  public String getNomeUser() {
    return this.nomeUser;
  }

  /**
   * Serve per settare il nome per la connessione al database.
   *
   * @param nomeUser è la variabile che contiene il nome per la connessione al database.
   */
  public void setNomeUser(String nomeUser) {
    this.nomeUser = nomeUser;
  }

  /**
   * Restituisce la password per la connessione al database.
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Serve per settare la password per la connessione al database.
   *
   * @param password è la variabile che contiene la password per la connessione al database.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Restituisce la porta per la connessione al database.
   */
  public int getPortaHost() {
    return this.portaHost;
  }

  /**
   * Serve per settare la porta per la connessione al database.
   *
   * @param portaHost è la variabile che contiene la porta per la connessione al database.
   */
  public void setPortaHost(int portaHost) {
    this.portaHost = portaHost;
  }

  /**
   * Restituisce il nome (indirizzo ip) per la connessione al database.
   */
  public String getNomeHost() {
    return this.nomeHost;
  }

  /**
   * Serve a settare il nome (indirizzo ip) per la connessione al database.
   *
   * @param nomeHost è la variabile che contiene il nome per la connessione al database.
   */
  public void setNomeHost(String nomeHost) {
    this.nomeHost = nomeHost;
  }
}