package Test.TestBean;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Bean.ComposizioneBean;
import Bean.OrdineBean;

/**
 * Classe di Test per ComposizioneBean.java.
 *
 * @author Alfonso Cuomo
 */
class TestComposizioneBean {

  @Test
  //Metodo che testa il costruttore della classe ComposizioneBean.java
  void testingCostruttoreOrdineBean() {
    System.out.println("testingCostruttoreComposizioneBean");
    ComposizioneBean exampleComposition = new ComposizioneBean(1, "isbn", 10);
    assertEquals(1, exampleComposition.getIdOrdine());
    assertEquals("isbn", exampleComposition.getIsbn());
    assertEquals(10, exampleComposition.getQuantita());
  }

  @Test
  //Metodo che testa i setter della classe ComposizioneBean.java.
  void testingSetterComposizioneBean() {
    System.out.println("testingSetterComposizioneBean"); 
    ComposizioneBean exampleComposition = new ComposizioneBean();

    int idOrdine = 1;
    exampleComposition.setIdOrdine(idOrdine);
    assertEquals(exampleComposition.getIdOrdine(), idOrdine, 1);

    String isbn = "isbn";
    exampleComposition.setIsbn(isbn);
    assertEquals(exampleComposition.getIsbn(), isbn);

    int quantita = 10;
    exampleComposition.setQuantita(quantita);
    assertEquals(exampleComposition.getQuantita(), quantita, 10);
  }
  
  @Test
  // Metodo che testa i getter della classe ComposizioneBean.java
  void testingGetterComposizioneBean() {
    System.out.println("testingGetterComposizioneBean");
    ComposizioneBean exampleComposition = new ComposizioneBean();
    
    int idOrdineExpResult = 1;
    exampleComposition.setIdOrdine(idOrdineExpResult);
    int resultIdOrdine = exampleComposition.getIdOrdine();
    assertEquals(idOrdineExpResult, resultIdOrdine, 1);
    
    String isbnExpResult = "isbn";
    exampleComposition.setIsbn(isbnExpResult);
    String resultIsbn = exampleComposition.getIsbn();
    assertEquals(isbnExpResult, resultIsbn);
  }
}
