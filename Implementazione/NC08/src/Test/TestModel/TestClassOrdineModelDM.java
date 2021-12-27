package Test.TestModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import Bean.OrdineBean;
import Model.OrdineModelDm;

/**
 * Classe Test per OrdineModelDm.java.
 *
 * @author Farina Simone
 *
 */

class TestClassOrdineModelDM {

  @Test
    void testDoRetrieveAll() throws SQLException {
    System.out.println("Testing :ORDINE: DoRetrieveAll()");

    OrdineModelDm dm = new OrdineModelDm();
    
    ArrayList<OrdineBean> ordini = dm.doRetrieveAll("dario@dodo.it");
    assertNotNull(ordini);
  }

  @Test
    void testDoSave() throws SQLException {
    System.out.println("Testing :ORDINE: DoSave()");

    OrdineModelDm dm = new OrdineModelDm();
    
    OrdineBean exampleOrdine = 
        new OrdineBean("2017-06-15", 1, "indirizzo", "dario@dodo.it", false, (float) 0);

    int i = dm.doSave(exampleOrdine);

    dm.doDelete(1);

    assertEquals(i, 1);
  }

  @Test
    void testDoDelete() throws SQLException {
    System.out.println("Testing :ORDINE: DoDelete()");

    OrdineModelDm dm = new OrdineModelDm();
    
    OrdineBean exampleOrdine = 
        new OrdineBean("2017-06-15", 1, "indirizzo", "dario@dodo.it", false, (float) 0);

    dm.doSave(exampleOrdine);

    boolean i = dm.doDelete(1);

    assertEquals(i, false);
  }
  
  @Test
    void testDoRetrieveByKey() throws SQLException {
    System.out.println("Testing :ORDINE: DoRetrieveBeKey");
    
    OrdineModelDm dm = new OrdineModelDm();
    
    OrdineBean exampleOrdine = 
        new OrdineBean("2017-06-15", 1, "indirizzo", "dario@dodo.it", false, (float) 0);

    dm.doSave(exampleOrdine);
    
    OrdineBean bean = (OrdineBean) dm.doRetrieveByKey(11);
    OrdineBean expected =
        new OrdineBean("2017-06-15", 11, "indirizzo", "dario@dodo.it", false, (float) 0);
    
    assertTrue(expected.getData().equals(bean.getData())
        && expected.getIdOrdine() == bean.getIdOrdine()
        && expected.getIndirizzo().equals(bean.getIndirizzo())
        && expected.getMail().equals(bean.getMail())
        && expected.getTotale() == bean.getTotale());
    
    dm.doDelete(1);
  }


}
