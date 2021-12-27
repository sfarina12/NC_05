/**
 * Classe test per ProdottoBean.
 */

package Test.TestBean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Bean.ProdottoBean;

/**
 * @author Alfonso Cuomo.
 *
 */
class TestProdottoBean {

  @Test

    //Metodo che testa il costruttore della classe ProdottoBean.java
    void testingCostruttoreProdottoBean() {
    System.out.println("testingCostruttoreProdottoBean");
    ProdottoBean exampleProduct = new ProdottoBean("isbn", "titolo", "autore", 23f, 
        null, "descrizione", "categoria", 50);
    assertEquals("isbn", exampleProduct.getIsbn());
    assertEquals("titolo", exampleProduct.getTitolo());
    assertEquals("autore", exampleProduct.getAutore());
    assertEquals(23f, exampleProduct.getPrezzo(), 0f);
    assertEquals(null, exampleProduct.getCopertina());
    assertEquals("descrizione", exampleProduct.getDescrizione());
    assertEquals("categoria", exampleProduct.getNomeCategoria());
    assertEquals(50, exampleProduct.getQuantitaStock());
  }

  @Test
     //Metodo che testa i setter della classe ProdottoBean.java.
     void testingSetterProdottoBean() {
    System.out.println("testingSetterProdottoBean"); 
    ProdottoBean exampleProduct = new ProdottoBean();

    String isbn = "isbn";
    exampleProduct.setIsbn(isbn);
    assertEquals(exampleProduct.getIsbn(), isbn);

    String autore = "autore";
    exampleProduct.setAutore(autore);
    assertEquals(exampleProduct.getAutore(), autore);

    String titolo = "titolo";
    exampleProduct.setTitolo(titolo);
    assertEquals(exampleProduct.getTitolo(), titolo);

    float prezzo = 23f;
    exampleProduct.setPrezzo(prezzo);
    assertEquals(exampleProduct.getPrezzo(), prezzo, 0f);

    int stock = 50;
    exampleProduct.setQuantitaStock(stock);
    assertEquals(exampleProduct.getQuantitaStock(), stock);

    byte[] copertina = null;
    exampleProduct.setCopertina(copertina);
    assertEquals(exampleProduct.getCopertina(), copertina);

    String descrizione = "descrizione";
    exampleProduct.setDescrizione(descrizione);
    assertEquals(exampleProduct.getDescrizione(), descrizione);

    String categoria = "categoria";
    exampleProduct.setNomeCategoria(categoria);
    assertEquals(exampleProduct.getNomeCategoria(), categoria);
  }

  @Test
  // Metodo che testa i getter della classe ProdottoBean.java
  void testingGetterProdottoBean() {
    System.out.println("testingGetterProdottoBean");
    ProdottoBean exampleProduct = new ProdottoBean();
    String isbnExpResult = "isbn";
    exampleProduct.setIsbn(isbnExpResult);
    String resultIsbn = exampleProduct.getIsbn();
    assertEquals(isbnExpResult, resultIsbn);
    
    String autoreExpResult = "autore";
    exampleProduct.setAutore(autoreExpResult);
    String resultAutore = exampleProduct.getAutore();
    assertEquals(autoreExpResult, resultAutore);
    
    byte[] copertinaExpResult = null;
    exampleProduct.setCopertina(copertinaExpResult);
    byte[] resultCopertina = exampleProduct.getCopertina();
    assertEquals(copertinaExpResult, resultCopertina);
    
    String descrizioneExpResult = "descrizione";
    exampleProduct.setDescrizione(descrizioneExpResult);
    String resultDescrizione = exampleProduct.getDescrizione();
    assertEquals(descrizioneExpResult, resultDescrizione);
    
    String categoriaExpResult = "categoria";
    exampleProduct.setNomeCategoria(categoriaExpResult);
    String resultCategoria = exampleProduct.getNomeCategoria();
    assertEquals(categoriaExpResult, resultCategoria);
    
    float prezzoExpResult = 23f;
    exampleProduct.setPrezzo(prezzoExpResult);
    float resultPrezzo = exampleProduct.getPrezzo();
    assertEquals(prezzoExpResult, resultPrezzo, 0f);
    
    int stockExpResult = 50;
    exampleProduct.setQuantitaStock(stockExpResult);
    int resultStock = exampleProduct.getQuantitaStock();
    assertEquals(stockExpResult, resultStock);
    
    String titoloExpResult = "titolo";
    exampleProduct.setTitolo(titoloExpResult);
    String resultTitolo = exampleProduct.getTitolo();
    assertEquals(titoloExpResult, resultTitolo);
  }

}

