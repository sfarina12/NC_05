package Bean;

/**
 * Rappresenta l'utente contenente le sue informazioni necessarie.
 *
 * @author Alfonso Cuomo
 */
public class UtenteBean {

  /**
   * Mail con la quale l'utente si è registrato.
   */
  private String mail;
  
  /**
   * Password con la quale l'utente si è registrato.
   */
  private String password;
  
  /**
   * Nickname a scelta dell'utente.
   */
  private String nickname;
  
  /**
   * Rappresenta se l'utente ha i diritti di admin.
   */
  private boolean isAdmin;

  
  /**
   * Restituisce la mail dell'utente.
   *
   * @return mail dell'utente.
   */
  public String getMail() {
    return mail;
  }

  /**
   * Modifica la mail dell'utente.
   *
   * @param mail mail dell'utente.
   */
  public void setMail(String mail) {
    this.mail = mail;
  }
  
  /**
   * Restituisce la password dell'utente.
   *
   * @return password dell'utente.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Modifica la password dell'utente.
   *
   * @param password password dell'utente.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Restituisce il nickname dell'utente.
   *
   * @return nickname dell'utente.
   */
  public String getNickname() {
    return nickname;
  }

  /**
   * Modifica il nickname dell'utente.
   *
   * @param nickname nickname dell'utente.
   */
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  /**
   * Restituisce il ruolo dell'utente.
   *
   * @return isAdmin ruolo dell'utente sotto forma di booleano.
   */
  public boolean isAdmin() {
    return isAdmin;
  }

  /**
   * Modifica il ruolo dell'utente.
   *
   * @param isAdmin nuovo ruolo dell'utente.
   */
  public void setAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  /**
   * Costruttore che richiede i vari parametri.
   *
   * @param mail mail dell'utente.
   * @param password password dell'utente.
   * @param nickname nickname dell'utente.
   * @param isAdmin ruolo dell'utente.
   */
  public UtenteBean(String mail, String password, String nickname, boolean isAdmin) {
    super();
    this.mail = mail;
    this.password = password;
    this.nickname = nickname;
    this.isAdmin = isAdmin;
  }

  public UtenteBean() {
    super();
  }
  
}
