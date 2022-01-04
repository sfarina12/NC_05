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
import Bean.ProdottoBean;
import Bean.UtenteBean;
import Model.ConnectionSingleton;
import Model.ProdottoModelDm;
import Model.UtenteModelDm;

/**
 * Classe Test per ProdottoModelDm.java.
 *
 * @author Alfonso Cuomo
 *
 */
class TestClassProdottoModelDm {

  ProdottoModelDm prodottoModel;
  ProdottoBean bean = new ProdottoBean("isbn", "titolo", 
      "autore", 10f, null, "descrizione", "categoria", 10);

  @BeforeEach
  public  void setUp() throws SQLException {
    prodottoModel = new ProdottoModelDm();
    ProdottoBean prova = prodottoModel.doRetrieveByKey("9781245562344");
    bean.setCopertina(prova.getCopertina());
    prodottoModel.doSave(this.bean);
  }
 
  @AfterEach
  public void setDown() throws SQLException {
    prodottoModel.doDelete(this.bean.getIsbn());
  }

  @Test
  void testDoRetrieveAll() throws SQLException {
    System.out.println("Testing DoRetrieveAll()");

    Collection<ProdottoBean> lista = new LinkedList<ProdottoBean>();
    lista = prodottoModel.doRetrieveAll();
    int size = lista.size();
    
    ProdottoBean bean2 = new ProdottoBean("isbn2", "titolo", 
            "autore", 10f, null, "descrizione", "categoria", 10);
    ProdottoBean prova = prodottoModel.doRetrieveByKey("9781245562344");
    bean2.setCopertina(prova.getCopertina());
    prodottoModel.doSave(bean2);
    
    Collection<ProdottoBean> listaExpected = new LinkedList<ProdottoBean>();
    listaExpected = prodottoModel.doRetrieveAll();
    int sizeExpected = listaExpected.size();
    assertEquals(size + 1 , sizeExpected);
    prodottoModel.doDelete("isbn2");
  }

  @Test
    void testDoSave() throws SQLException {
    System.out.println("Testing DoSave() di ProdottoModelDm.java");
    
    byte[] bt;
    ProdottoBean bean2 = prodottoModel.doRetrieveByKey("9781245562344");
    bt = bean2.getCopertina();
    
    ProdottoBean beanExpected = new ProdottoBean("isbn2", "titolo", "autore", 23f, 
        bt, "descrizione", "categoria", 50);
    int i = prodottoModel.doSave(beanExpected);
    
    assertEquals(i, 1);
    prodottoModel.doDelete("isbn2");
  }

  @Test
    void testDoDelete() throws SQLException {
    System.out.println("Testing DoDelete() di ProdottoModelDm.java");

    ProdottoBean bean2 = new ProdottoBean("isbn2", "titolo", 
            "autore", 10f, null, "descrizione", "categoria", 10);
    ProdottoBean prova = prodottoModel.doRetrieveByKey("9781245562344");
    bean2.setCopertina(prova.getCopertina());
    prodottoModel.doSave(bean2);
    boolean i = prodottoModel.doDelete(bean2.getIsbn());

    assertTrue(i);    
  }

  @Test
    void testDoRetrieveByKey() throws SQLException {
    System.out.println("Testing DoRetrieveBeKey");
    
    ProdottoBean bean2 = prodottoModel.doRetrieveByKey(this.bean.getIsbn());
    String isbnExpected = "isbn";
    
    assertEquals(isbnExpected, bean2.getIsbn());
  }
  
  @Test
  void testUpdateCopertina() throws SQLException, IOException {
  System.out.println("Testing testUpdateCopertina..");
  
  assertNotNull(prodottoModel.updateCopertina("isbn", ".\\WebContent\\WEB-INF\\copertine\\9781245562344.jpg"));
  }
}
