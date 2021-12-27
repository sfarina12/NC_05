package Test.TestModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import Bean.ProdottoBean;
import Model.ProdottoModelDm;
import Bean.UtenteBean;
import Model.UtenteModelDm;

/**
 * Classe Tester per la classe UtenteModelDm.java.
 *
 * @author Farina Simone
 */
class TestClassUtenteModelDm {

  @Test
  void testDoSave() throws SQLException {
    System.out.println("Testing :UTENTE: testDoSave..");

    UtenteBean bean = new UtenteBean("bellazio@bro", "12345a", "bro", false);
    
    /*UtenteModelDm dm=Mockito.mock(UtenteModelDm.class);
    
    when(dm.doSave(bean)).thenReturn(1);*/
    
    int i = (int) UtenteModelDm.doQuery("doSave", bean);

    UtenteModelDm.doQuery("doDelete", bean.getMail());
    assertEquals(i, 1); 
  }
  
  @Test
  void testDoRetrieveByKey() throws SQLException {
  System.out.println("Testing testDoRetreieveByKey..");

  UtenteBean bean = (UtenteBean) UtenteModelDm.doQuery("doRetrieveByKey", "asia@dodo.it");
  UtenteBean expected =
    new UtenteBean("asia@dodo.it", "password", "Asietta", false);

  assertTrue(expected.getMail().equals(bean.getMail())
  && expected.getNickname().equals(bean.getNickname())
  && expected.getPassword().equals(bean.getPassword())
  && expected.isAdmin() == bean.isAdmin());
  }  

  @Test
  void testDoChecklogin() throws SQLException {
  System.out.println("Testing testDoCheckLogin..");
  }
  
  @Test
  void testDoDelete() throws SQLException {
  System.out.println("Testing testDoDelete...");

  UtenteBean bean = new UtenteBean("bellazio@bro", "12345a", "bro", false);
  UtenteModelDm.doQuery("doSave", bean);
  
  boolean i = (boolean)  UtenteModelDm.doQuery("doDelete", bean.getMail());

  assertTrue(i);
  }
}
