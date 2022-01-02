package Test.TestModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

class TestClassOrdineModelDM {

  @Test
    void testDoRetrieveAll() throws SQLException {
    System.out.println("Testing :ORDINE: DoRetrieveAll()");

    OrdineBean bean1 = new OrdineBean("data", 1, 
            "indirizzo", "mail", true, 10f);
 
    OrdineModelDm dm = Mockito.mock(OrdineModelDm.class);
    ArrayList<OrdineBean> lista = new ArrayList<OrdineBean>();
    lista.add(bean1);

    Mockito.when(dm.doRetrieveAll(bean1.getMail())).thenReturn(lista);
    assertEquals(lista, dm.doRetrieveAll(bean1.getMail()));   
  }

  @Test
    void testDoSave() throws SQLException {
    System.out.println("Testing :ORDINE: DoSave()");
    
    OrdineBean exampleOrdine = 
            new OrdineBean("2017-06-15", 1, "indirizzo", "dario@dodo.it", false, (float) 0);
    
    UtenteBean dmU = Mockito.mock(UtenteBean.class);   
    OrdineModelDm dm = Mockito.mock(OrdineModelDm.class);
    ArrayList<OrdineBean> lista = new ArrayList<OrdineBean>();
    lista.add(exampleOrdine);

    Mockito.when(dm.doSave(exampleOrdine)).thenReturn(1);
    assertNotEquals(0, dm.doSave(exampleOrdine));  
  }

  @Test
    void testDoDelete() throws SQLException {
    System.out.println("Testing :ORDINE: DoDelete()");

    OrdineModelDm ordineModelMock = Mockito.mock(OrdineModelDm.class);
    OrdineBean exampleOrdine = 
        new OrdineBean("2017-06-15", 1, "indirizzo", "dario@dodo.it", false, (float) 0);
    ArrayList<OrdineBean> lista = new ArrayList<OrdineBean>();
    lista.add(exampleOrdine);
    
    Mockito.when(ordineModelMock.doDelete(exampleOrdine.getIdOrdine())).thenReturn(false);
    assertEquals(false, ordineModelMock.doDelete(exampleOrdine.getIdOrdine()));
  }
  
  @Test
    void testDoRetrieveByKey() throws SQLException {
    System.out.println("Testing :ORDINE: DoRetrieveBeKey");
    
    OrdineModelDm ordineModelMock = Mockito.mock(OrdineModelDm.class);
    OrdineBean exampleOrdine = 
        new OrdineBean("2017-06-15", 1, "indirizzo", "dario@dodo.it", false, (float) 0);
    
    ArrayList<OrdineBean> lista = new ArrayList<OrdineBean>();
    lista.add(exampleOrdine);
    
    Mockito.when(ordineModelMock.doRetrieveByKey(exampleOrdine.getIdOrdine()))
        .thenReturn(exampleOrdine);
    assertEquals(exampleOrdine, ordineModelMock.doRetrieveByKey(exampleOrdine.getIdOrdine()));
  }
}