package Test.TestModel;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import Bean.OrdineBean;
import Bean.UtenteBean;
import Model.UtenteModelDm;

/**
 * Classe Tester per la classe UtenteModelDm.java.
 *
 * @author Alfonso Cuomo
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestClassUtenteModelDm {
	
  UtenteModelDm utenteModel;
  UtenteBean bean = new UtenteBean("bellazio@bro", "12345a", "bro", false);
	
  @BeforeEach
  public  void setUp() throws SQLException {
    utenteModel = new UtenteModelDm();
    utenteModel.doSave(this.bean);
  }
  
  @AfterEach
  public void setDown() throws SQLException {
	  utenteModel.doDelete(this.bean.getMail());
  }
  
  @Test
  void testDoSave() throws SQLException {
    System.out.println("Testing :UTENTE: testDoSave..");
    UtenteBean bean = new UtenteBean("123bellazio@bro", "12345a", "bro", false);
    int i = utenteModel.doSave(bean);
    
    assertEquals(1, i);
    utenteModel.doDelete(bean.getMail());
  }
  
  @Test
  void testDoDelete() throws SQLException {
    System.out.println("Testing testDoDelete...");
    
    assertNotNull(utenteModel.doDelete(bean.getMail()));
    utenteModel.doSave(bean);
  }
  
  @Test
  void testDoChecklogin() throws SQLException {
    System.out.println("Testing testDoCheckLogin..");
    
    UtenteBean beanExpect;
    beanExpect = utenteModel.doCheckLogin("bellazio@bro", "12345a");
 
    assertTrue(bean.getMail().equals(beanExpect.getMail())
            && bean.getNickname().equals(beanExpect.getNickname())
            && bean.getPassword().equals(beanExpect.getPassword())
            && bean.isAdmin() == beanExpect.isAdmin());
  }
  
  @Test
  void testDoRetrieveByKey() throws SQLException {
    System.out.println("Testing testDoRetreieveByKey..");

    UtenteBean bean = (UtenteBean) utenteModel.doRetrieveByKey("bellazio@bro");    
    UtenteBean expected =
        new UtenteBean("bellazio@bro", "12345a", "bro", false);

    assertTrue(expected.getMail().equals(bean.getMail())
        && expected.getNickname().equals(bean.getNickname())
        && expected.getPassword().equals(bean.getPassword())
        && expected.isAdmin() == bean.isAdmin());
  }  
}