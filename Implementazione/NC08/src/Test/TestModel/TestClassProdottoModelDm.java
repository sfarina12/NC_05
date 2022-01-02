package Test.TestModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import Bean.ProdottoBean;
import Model.ConnectionSingleton;
import Model.ProdottoModelDm;

import org.apache.catalina.tribes.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Classe Test per ProdottoModelDm.java.
 *
 * @author Farina Simone
 *
 */
class TestClassProdottoModelDm {

  @Test
  void testDoRetrieveAll() throws SQLException {
    System.out.println("Testing DoRetrieveAll()");

    ProdottoBean bean1 = new ProdottoBean("isbn", "titolo", 
        "autore", 10f, null, "descrizione", "categoria", 10);
    ProdottoBean bean2 = new ProdottoBean("isbn", "titolo", 
        "autore", 10f, null, "descrizione", "categoria", 10);	 

    ProdottoModelDm prodottoModelMock = Mockito.mock(ProdottoModelDm.class);
    ArrayList<ProdottoBean> lista = new ArrayList<ProdottoBean>();
    lista.add(bean1);
    lista.add(bean2);

    Mockito.when(prodottoModelMock.doRetrieveAll()).thenReturn(lista);
    assertEquals(lista, prodottoModelMock.doRetrieveAll());   
    //System.out.println(lista);
  }

  @Test
    void testDoSave() throws SQLException {
    System.out.println("Testing DoSave() di ProdottoModelDm.java");
    
    ProdottoBean bean1 = new ProdottoBean("isbn", "titolo", 
        "autore", 10f, null, "descrizione", "categoria", 10);
    ProdottoModelDm prodottoModelMock = Mockito.mock(ProdottoModelDm.class);
    
    Mockito.when(prodottoModelMock.doSave(bean1)).thenReturn(1);
    assertEquals(1, prodottoModelMock.doSave(bean1));   
  }

  @Test
    void testDoDelete() throws SQLException {
    System.out.println("Testing DoDelete() di ProdottoModelDm.java");

    ProdottoBean bean1 = new ProdottoBean("isbn", "titolo", 
        "autore", 10f, null, "descrizione", "categoria", 10);
    ProdottoModelDm prodottoModelMock = Mockito.mock(ProdottoModelDm.class);

    Mockito.when(prodottoModelMock.doDelete(bean1.getIsbn())).thenReturn(true);
    assertTrue(prodottoModelMock.doDelete(bean1.getIsbn()));  
  }

  @Test
    void testDoRetrieveByKey() throws SQLException {
    System.out.println("Testing DoRetrieveBeKey");
    ProdottoBean bean1 = new ProdottoBean("isbn", "titolo", 
        "autore", 10f, null, "descrizione", "categoria", 10);
    ProdottoModelDm prodottoModelMock = Mockito.mock(ProdottoModelDm.class);

    Mockito.when(prodottoModelMock.doRetrieveByKey(bean1.getIsbn())).thenReturn(bean1);
    assertEquals(bean1, prodottoModelMock.doRetrieveByKey(bean1.getIsbn()));  
  }
}
