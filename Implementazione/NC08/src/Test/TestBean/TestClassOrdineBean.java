package Test.TestBean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Bean.OrdineBean;
import Bean.ProdottoBean;

/**
 * Classe di Test per OrdineBean.java.
 *
 * @author Alfonso Cuomo
 */
class TestOrdineBean {

  @Test
    //Metodo che testa il costruttore della classe OrdineBean.java
    void testingCostruttoreOrdineBean() {
    System.out.println("testingCostruttoreOrdineBean");
    OrdineBean exampleOrder = new OrdineBean("data", 1, "indirizzo", "mail", 
        true, 100f);
    assertEquals("data", exampleOrder.getData());
    assertEquals(1, exampleOrder.getIdOrdine(), 1);
    assertEquals("indirizzo", exampleOrder.getIndirizzo());
    assertEquals("mail", exampleOrder.getMail());
    assertEquals(true, exampleOrder.isMetodoPagamento());
    assertEquals(100f, exampleOrder.getTotale());
  }

  @Test
   //Metodo che testa i setter della classe OrdineBean.java.
   void testingSetterOrdineBean() {
    System.out.println("testingSetterOrdineBean"); 
    OrdineBean exampleOrder = new OrdineBean();

    String data = "data";
    exampleOrder.setData(data);
    assertEquals(exampleOrder.getData(), data);

    int idOrdine = 1;
    exampleOrder.setIdOrdine(idOrdine);
    assertEquals(exampleOrder.getIdOrdine(), idOrdine, 1);

    String indirizzo = "indirizzo";
    exampleOrder.setIndirizzo(indirizzo);
    assertEquals(exampleOrder.getIndirizzo(), indirizzo);

    String mail = "mail";
    exampleOrder.setMail(mail);
    assertEquals(exampleOrder.getMail(), mail);

    boolean metodoPagamento = true;
    exampleOrder.setMetodoPagamento(metodoPagamento);
    assertEquals(exampleOrder.isMetodoPagamento(), metodoPagamento);

    float totale = 100f;
    exampleOrder.setTotale(totale);
    assertEquals(exampleOrder.getTotale(), totale);
  }
  
  @Test
  // Metodo che testa i getter della classe OrdineBean.java
  void testingGetterOrdineBean() {
    System.out.println("testingGetterOrdineBean");
    OrdineBean exampleOrder = new OrdineBean();
    String dataExpResult = "data";
    exampleOrder.setData(dataExpResult);
    String resultData = exampleOrder.getData();
    assertEquals(dataExpResult, resultData);
    
    int idOrdineExpResult = 1;
    exampleOrder.setIdOrdine(idOrdineExpResult);
    int resultIdOrdine = exampleOrder.getIdOrdine();
    assertEquals(idOrdineExpResult, resultIdOrdine, 1);
    
    String indirizzoExpResult = "indirzzo";
    exampleOrder.setIndirizzo(indirizzoExpResult);
    String resultIndirizzo = exampleOrder.getIndirizzo();
    assertEquals(indirizzoExpResult, resultIndirizzo);
    
    String mailExpResult = "mail";
    exampleOrder.setMail(mailExpResult);
    String resultMail = exampleOrder.getMail();
    assertEquals(mailExpResult, resultMail);
    
    boolean metodoPagamentoExpResult = true;
    exampleOrder.setMetodoPagamento(metodoPagamentoExpResult);
    boolean resultMetodoPagamento = exampleOrder.isMetodoPagamento();
    assertEquals(metodoPagamentoExpResult, resultMetodoPagamento);
    
    float totaleExpResult = 23f;
    exampleOrder.setTotale(totaleExpResult);
    float resultTotale = exampleOrder.getTotale();
    assertEquals(totaleExpResult, resultTotale, 23f);
  }
  
}
