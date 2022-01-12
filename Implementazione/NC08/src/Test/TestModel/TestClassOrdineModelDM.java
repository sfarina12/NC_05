package Test.TestModel;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Bean.OrdineBean;
import Bean.ProdottoBean;
import Bean.UtenteBean;
import Model.OrdineModelDm;
import Model.ProdottoModelDm;

/**
 * Classe Test per OrdineModelDm.java.
 *
 * @author Alfonso Cuomo 
 *
 */

class TestClassOrdineModelDm {

  OrdineModelDm ordineModel;
  OrdineBean order = new OrdineBean("0000-00-00", 1, 
          "indirizzo", "asia@dodo.it", true, 10f);

  @BeforeEach
    public  void setUp() throws SQLException {
    ordineModel = new OrdineModelDm();
    ordineModel.doSave(this.order);
  }

  @AfterEach
    public void setDown() throws SQLException {
    ordineModel.doDelete(this.order.getIdOrdine());
  }

  @Test
    void testDoRetrieveAll() throws SQLException {
    System.out.println("Testing :ORDINE: DoRetrieveAll()");
    
    ArrayList<OrdineBean> lista = new ArrayList<OrdineBean>();
    OrdineBean orderPlus = new OrdineBean("1-1-1", 2, 
            "indirizzo", "asia@dodo.it", true, 10f);
    OrdineBean nonInLista = new OrdineBean("2-2-2", 3, 
            "indirizzo", "dario@dodo.it", true, 10f);
    ordineModel.doSave(orderPlus);
    ordineModel.doSave(nonInLista);
    lista = ordineModel.doRetrieveAll("asia@dodo.it");
    
    assertEquals(6, lista.size()-1);
    ordineModel.doDelete(orderPlus.getIdOrdine());
    ordineModel.doDelete(nonInLista.getIdOrdine());
  }

  @Test
    void testDoSave() throws SQLException {
    System.out.println("Testing :ORDINE: DoSave()");
    
    OrdineBean order2 = new OrdineBean("10-12-12", 6, 
            "indirizzo", "asia@dodo.it", true, 10f);
    int i = ordineModel.doSave(order2);
    
    assertEquals(1, i);
    ordineModel.doDelete(order2.getIdOrdine());
  }

  @Test
    void testDoDelete() throws SQLException {
    System.out.println("Testing :ORDINE: DoDelete()");

    assertNotNull(ordineModel.doDelete(order.getIdOrdine()));
    ordineModel.doSave(order);
  }
  
  @Test
    void testDoRetrieveByKey() throws SQLException {
    System.out.println("Testing :ORDINE: DoRetrieveBeKey()");
    
    OrdineBean ordine = ordineModel.doRetrieveByKey(order.getIdOrdine());
    assertTrue("0000-00-00".equals(ordine.getData()));
    assertTrue(1 == (ordine.getIdOrdine()));
    assertTrue("indirizzo".equals(ordine.getIndirizzo()));
    assertTrue("asia@dodo.it".equals(ordine.getMail()));
    assertTrue(true == ordine.isMetodoPagamento());
    assertTrue(10f == ordine.getTotale());
  }
}