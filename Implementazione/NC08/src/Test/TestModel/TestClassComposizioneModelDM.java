package Test.TestModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Bean.ComposizioneBean;
import Bean.OrdineBean;
import Bean.ProdottoBean;
import Model.ComposizioneModelDm;
import Model.OrdineModelDm;
import Model.ProdottoModelDm;

/**
 * Classe Test per OrdineModelDm.java.
 *
 * @author Alfonso Cuomo
 *
 */
class TestClassComposizioneModelDm {

  ProdottoModelDm prodottoModel;
  ProdottoBean prodotto = new ProdottoBean("isbn1", "titolo", 
            "autore", 10f, null, "descrizione", "categoria", 10);
  OrdineModelDm ordineModel;
  OrdineBean order = new OrdineBean("0000-00-00", 1, 
            "indirizzo", "asia@dodo.it", true, 10f);
  ComposizioneModelDm composizioneModel;
  ComposizioneBean bean = new ComposizioneBean(1, "isbn", 10);

  @BeforeEach
  public  void setUp() throws SQLException {

    prodottoModel = new ProdottoModelDm();
    ProdottoBean copertina = prodottoModel.doRetrieveByKey("9781245562344");
    prodotto.setCopertina(copertina.getCopertina());
    prodottoModel.doSave(prodotto);

    ordineModel = new OrdineModelDm();
    ordineModel.doSave(this.order);

    composizioneModel = new ComposizioneModelDm();
    composizioneModel.doSave(this.bean);
  }

  @AfterEach
  public void setDown() throws SQLException {

	composizioneModel.doDelete(this.bean.getIdOrdine());  
    prodottoModel.doDelete(this.prodotto.getIsbn());
    ordineModel.doDelete(this.order.getIdOrdine());
  }

  @Test
    void testDoSave() throws SQLException {
    System.out.println("Testing :COMPOSIZIONE: DoSave()");
    
    // vedere setUp()
  }

  @Test
    void testDoDelete() throws SQLException {
    System.out.println("Testing :ORDINE: DoDelete()");
    
    // vedere setDown()
  }

  @Test
    void testDoRetrieveByKey() throws SQLException {
    System.out.println("Testing :COMPOSIZIONE: DoRetrieveBeKey");
    
    ComposizioneBean composizione = composizioneModel.doRetrieveByKey(this.bean.getIdOrdine());
    assertTrue(1 == composizione.getIdOrdine());
    assertTrue("isbn".equals(composizione.getIsbn()));
    assertTrue(10 == composizione.getQuantita());
  }
  
  
  
  /* @Test
  void testDoRetrieveAll() throws SQLException {
  System.out.println("Testing :COMPOSIZIONE: DoRetrieveAll()");

  ArrayList<ComposizioneBean> lista = new ArrayList<ComposizioneBean>();
  ComposizioneBean beanPlus = new ComposizioneBean(1, "isbn1", 50); 
  ComposizioneBean beanPlusPlus = new OrdineBean(2, "isbn2", 80); 

  ordineModel.doSave(orderPlus);
  ordineModel.doSave(nonInLista);
  lista = ordineModel.doRetrieveAll("asia@dodo.it");
  
  assertEquals(2, lista.size());
  ordineModel.doDelete(orderPlus.getIdOrdine());
  ordineModel.doDelete(nonInLista.getIdOrdine());
  
}*/
}
