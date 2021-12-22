package Bean;

/**
 * Contiene tutte le informazioni riguardo i libri che compongono l'ordine.
 *
 * @author Alfonso Cuomo
 */
public class ComposizioneBean {

  /** id dell'ordine. */
  private int idOrdine;
  
  /** isbn del prodotto. */
  private String isbn;
  
  /** quantit� di prodotti dell'ordine.  */
  private int quantita;

  
  /**
   * Metodo che restituisce il codice id del ordine.
   *
   * @return Metodo che restituisce il codice id del ordine.
   */
  public int getIdOrdine() {
    return idOrdine;
  }

  /**
   * Nuovo id all'odine.
   *
   * @param idOrdine - nuovo id all'odine.
   */
  public void setIdOrdine(int idOrdine) {
    this.idOrdine = idOrdine;
  }

  /**
   * Metodo che restituisce il codice isbn del prodotto.
   *
   * @return Metodo che restituisce il codice isbn del prodotto. 
   */
  public String getIsbn() {
    return isbn;
  }
  
  /**
   * nuovo isbn da collegare all'odine.
   *
   * @param isbn - nuovo isbn da collegare all'odine.
   */
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }
  
  /**
   * Metodo che restituisce la quantit� di prodotti all'interno dell'ordine.
   *
   * @return Metodo che restituisce la quantit� di prodotti all'interno dell'ordine. 
   */
  public int getQuantita() {
    return quantita;
  }
  
  /** 
   * Metodo che restituisce la quantit� di prodotti all'interno dell'ordine.
   *
   * @param quantita - nuova quantit� di prodotti prensenti nell'odine 
   */
  public void setQuantita(int quantita) {
    this.quantita = quantita;
  }

  
  /**
   * costruttore con tutti i campi.
   *
   * @param idOrdine - id dell'ordine.
   * @param isbn - isbn del prodotto.
   * @param quantita - quantit� di prodotti.
   */
  public ComposizioneBean(int idOrdine, String isbn, int quantita) {
    super();
    this.idOrdine = idOrdine;
    this.isbn = isbn;
    this.quantita = quantita;
  }

  public ComposizioneBean() {
    super();
  }
  
}
