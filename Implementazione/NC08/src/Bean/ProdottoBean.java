package Bean;

/**
 * La seguente classe rappresenta un prodotto dello store, in particolare un libro.
 *
 * @author Alfonso Cuomo
 *
 */
public class ProdottoBean {
  /**
  * Codice isbn relativo al prodotto.
  */
  private String isbn;
  
  /**
   * Nome del titolo relativo al prodotto.
   */
  private String titolo;
  
  /**
   * Nome dell'autore che ha scritto il prodotto. 
   */
  private String autore;
  
  /**
   * Valore del prodotto.
   */
  private float prezzo;
  
  /**
   * Nome della copertina del prodotto.
   */
  private byte[] copertina;
  
  /**
   * Descrizione breve del prodotto.
   */
  private String descrizione;
  
  /**
   * Nome della categoria di cui fa parte il prodotto.
   */
  private String nomeCategoria;
  
  /**
   * Quantità disponibile del prodotto all' interno del negozio online. 
   */
  private int quantitaStock;
  
  /**
   * Restituisce il codice isbn del prodotto selezionato.
   */
  public String getIsbn() {
    return isbn;
  }
  
  /**
   * Metodo che setta il codice isbn del prodotto.
   *
   * @param isbn  codice isbn del prodotto. 
   */
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }
  
  /**
   * Metodo che restituisce il nome del titolo del prodotto selezionato.
   *
   * @return restituisce il nome del titolo del prodotto selezionato.
   */
  public String getTitolo() {
    return titolo;
  }
  
  /**
   * Metodo che setta il nome del titolo del prodotto.
   *
   * @param titolo nome del titolo del prodotto. 
   */
  public void setTitolo(String titolo) {
    this.titolo = titolo;
  }
  
  /**
   * Metodo che restituisce il nome dell'autore del prodotto selezionato.
   *
   * @return restituisce il nome dell'autore del prodotto selezionato.
   */
  public String getAutore() {
    return autore;
  }
  
  /**
   * Metodo che setta il nome dell'autore.
   *
   * @param autore nome dell'autore.
   */
  public void setAutore(String autore) {
    this.autore = autore;
  }
  
  /**
   * Metodo che restituisce il valore in formato float del prodotto preso in considerazione.
   *
   * @return restituisce il valore in formato float del prodotto preso in considerazione.
   */
  public float getPrezzo() {
    return prezzo;
  }
  
  /**
   * Metodo che setta il prezzo del prodotto selezionato.
   *
   * @param prezzo valore del prodotto.
   */
  public void setPrezzo(float prezzo) {
    this.prezzo = prezzo;
  }
  
  /**
   * Metodo che restituisce la stringa relativa alla copertina del prodotto.
   *
   * @return restituisce la stringa relativa alla copertina del prodotto.
   */
  public byte[] getCopertina() {
    return copertina;
  }
  
  /**
   * Metodo che setta la stringa della copertina del prodotto.
   *
   * @param copertina stringa relativa alla copertina del prodotto.
   */
  public void setCopertina(byte[] copertina) {
    this.copertina = copertina;
  }
  
  /**
   * Metodo che restituisce la descrizione (breve) relativa al prodotto selezionato.
   *
   * @return restituisce la descrizione (breve) relativa al prodotto selezionato. 
   */
  public String getDescrizione() {
    return descrizione;
  }
  
  /**
   * Metodo che setta la descrizione del prodotto.
   *
   * @param descrizione descrizione del prodotto.
   */
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }
  
  /**
   * Metodo che restituisce il nome della categoria di cui fa parte il prodotto.
   *
   * @return restituisce il nome della categoria di cui fa parte il prodotto.
   */
  public String getNomeCategoria() {
    return nomeCategoria;
  }
  
  /**
   * Metodo che setta il nome della categoria del prodotto.
   *
   * @param nomeCategoria nome della categoria del prodotto.
   */
  public void setNomeCategoria(String nomeCategoria) {
    this.nomeCategoria = nomeCategoria;
  }
  
  /**
   * Metodo che restituisce il numero di prodotti disponibili nell'inventario virtuale.
   *
   * @return restituisce il numero di prodotti disponibili nell'inventario virtuale.
   */
  public int getQuantitaStock() {
    return quantitaStock;
  }
  
  /**
   * Metodo che setta la quantità di un prodotto.
   *
   * @param quantitaStock setta la quantità di un prodotto.
   */
  public void setQuantitaStock(int quantitaStock) {
    this.quantitaStock = quantitaStock;
  }
  
  /**
   * Costruttore che richiede vari parametri.
   *
   * @param isbn isbn del libro
   * @param titolo titolo del libro
   * @param autore autore del libro
   * @param prezzo prezzo del libro
   * @param copertina copertina del libro
   * @param descrizione descrizione del libro
   * @param nomeCategoria nome della categoria del libro
   * @param quantitaStock quantità nello stock.
   */
  public ProdottoBean(String isbn, String titolo, String autore, float prezzo,
      byte[] copertina, String descrizione, String nomeCategoria, int quantitaStock) {
    super();
    this.isbn = isbn;
    this.titolo = titolo;
    this.autore = autore;
    this.prezzo = prezzo;
    this.copertina = copertina;
    this.descrizione = descrizione;
    this.nomeCategoria = nomeCategoria;
    this.quantitaStock = quantitaStock;
  }

  public ProdottoBean() {
    super();
  }
  
}

