package Bean;

/**
 * Classe bean che contiene tuttle le informazioni riguardo l'ordine.
 *@author Alfonso Cuomo
 */
public class OrdineBean {

  private String data;
  private int idOrdine;
  private String indirizzo;
  private String mail;
  private boolean metodoPagamento;
  private float totale;

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
  
  public int getIdOrdine() {
    return idOrdine;
  }

  public void setIdOrdine(int idOrdine) {
    this.idOrdine = idOrdine;
  }

  public String getIndirizzo() {
    return indirizzo;
  }

  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public boolean isMetodoPagamento() {
    return metodoPagamento;
  }
  
  public void setMetodoPagamento(boolean metodoPagamento) {
    this.metodoPagamento = metodoPagamento;
  }

  public float getTotale() {
    return totale;
  }

  public void setTotale(float totale) {
    this.totale = totale;
  }

  public OrdineBean(String data, int idOrdine, String indirizzo, String mail,
        boolean metodoPagamento, float totale) {
    super();
    this.data = data;
    this.idOrdine = idOrdine;
    this.indirizzo = indirizzo;
    this.mail = mail;
    this.metodoPagamento = metodoPagamento;
    this.totale = totale;
  }

  public OrdineBean() {
    super();
  }
}

