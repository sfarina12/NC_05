package Test.TestModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Collection;
import Bean.ProdottoBean;
import Model.ProdottoModelDm;
import org.junit.jupiter.api.Test;

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

    Collection<ProdottoBean> products = 
        (Collection<ProdottoBean>) ProdottoModelDm.doQuery("doRetrieveAll", null);
    assertNotNull(products);
  }

  @Test
    void testDoSave() throws SQLException {
    System.out.println("Testing DoSave() di ProdottoModelDm.java");

    byte[] bt;
    ProdottoBean bean = (ProdottoBean) ProdottoModelDm.doQuery("doRetrieveByKey", "9781245562344");
    bt = bean.getCopertina();
    
    ProdottoBean exampleProduct = new ProdottoBean("isbn", "titolo", "autore", 23f, 
        bt, "descrizione", "categoria", 50);

    int i = (int) ProdottoModelDm.doQuery("doSave", exampleProduct);

    ProdottoModelDm.doQuery("doDelete", exampleProduct.getIsbn());

    assertEquals(i, 1);
  }

  @Test
    void testDoDelete() throws SQLException {
    System.out.println("Testing DoDelete() di ProdottoModelDm.java");

    byte[] bt;
    ProdottoBean bean = (ProdottoBean) ProdottoModelDm.doQuery("doRetrieveByKey", "9781245562344");
    bt = bean.getCopertina();
    
    ProdottoBean exampleProduct = new ProdottoBean("isbn", "titolo", "autore", 23f, 
        bt, "descrizione", "categoria", 50);

    boolean i = (boolean)  ProdottoModelDm.doQuery("doDelete", exampleProduct.getIsbn());

    assertTrue(i);
  }

  @Test
    void testDoRetrieveByKey() throws SQLException {
    System.out.println("Testing DoRetrieveBeKey");
    ProdottoBean bean = (ProdottoBean) ProdottoModelDm.doQuery("doRetrieveByKey", "9781245562344");
    ProdottoBean expected =
        new ProdottoBean("9781245562344", "Alla ricerca del tempo perduto",
          "Marcel Proust", 30f, null, "Lorem ipsum dolor sit amet,"
          + " consectetur adipiscing elit, sed do eiusmod "
          + "tempor incididunt ut labore et dolore magna aliqua."
          + " Ut enim ad minim veniam, quis nostrud exercitation "
          + "ullamco laboris nisi ut aliquip ex ea"
          + " commodo consequat.", "Narrativa straniera", 4);
    
    assertTrue(expected.getIsbn().equals(bean.getIsbn())
        && expected.getTitolo().equals(bean.getTitolo())
        && expected.getAutore().equals(bean.getAutore())
        && expected.getPrezzo() == bean.getPrezzo()
        && expected.getDescrizione().equals(bean.getDescrizione())
        && expected.getQuantitaStock() == bean.getQuantitaStock()
        && expected.getNomeCategoria().equals(bean.getNomeCategoria()));
  }
  
  @Test
    void testUpdateCopertina() throws SQLException {
    System.out.println("Testing testUpdateCopertina..");
   /* byte[] bt;
    ProdottoBean bean = (ProdottoBean) ProdottoModelDm.doQuery("doRetrieveByKey", "9781245562344");
    bt = bean.getCopertina();*/

  }
}