package Test.TestModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
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
    
    UtenteModelDm dm = Mockito.mock(UtenteModelDm.class);
    when(dm.doSave(bean)).thenReturn(1);
    assertEquals(1, dm.doSave(bean)); 
  }
  
  @Test
  void testDoRetrieveByKey() throws SQLException {
    System.out.println("Testing testDoRetreieveByKey..");

    UtenteBean bean = new UtenteBean("bellazio@bro", "12345a", "bro", false);
    
    UtenteModelDm dm = Mockito.mock(UtenteModelDm.class);
    when(dm.doRetrieveByKey(bean.getMail())).thenReturn(bean);
    assertEquals(bean, dm.doRetrieveByKey(bean.getMail()));  
  }  

  @Test
  void testDoChecklogin() throws SQLException {
    System.out.println("Testing testDoCheckLogin..");
    
    UtenteBean bean = new UtenteBean("bellazio@bro", "12345a", "bro", false);
    Connection conn = null;
    
    UtenteModelDm dm = Mockito.mock(UtenteModelDm.class);
    when(dm.doCheckLogin(conn, bean.getMail(), bean.getPassword())).thenReturn(bean);
    assertEquals(bean, dm.doCheckLogin(conn, bean.getMail(), bean.getPassword()));
  }
  
  @Test
  void testDoDelete() throws SQLException {
    System.out.println("Testing testDoDelete...");

    UtenteBean bean = new UtenteBean("bellazio@bro", "12345a", "bro", false);
    
    UtenteModelDm dm = Mockito.mock(UtenteModelDm.class);
    when(dm.doDelete(bean.getMail())).thenReturn(true);
    assertEquals(true, dm.doDelete(bean.getMail()));
  }
}
