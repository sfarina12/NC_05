package Test.TestModel;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import Bean.ComposizioneBean;
import Model.ComposizioneModelDm;


class TestClassComposizioneModelDM {

  /**
   * Classe Test per OrdineModelDm.java.
   *
   * @author Alfonso Cuomo
   *
   */

  @Test
    void testDoRetrieveAll() throws SQLException {
    System.out.println("Testing :COMPOSIZIONE: DoRetrieveAll()");

    ComposizioneModelDm mock = Mockito.mock(ComposizioneModelDm.class);
    
    ComposizioneBean bean1 = new ComposizioneBean(1, "isbn", 10);
    
    ArrayList<ComposizioneBean> lista = new ArrayList<ComposizioneBean>();
    lista.add(bean1);

    Mockito.when(mock.doRetrieveAll(bean1.getIdOrdine())).thenReturn(lista);
    assertEquals(lista, mock.doRetrieveAll(bean1.getIdOrdine()));
    
  }
  
  @Test
    void testDoSave() throws SQLException {
    System.out.println("Testing :COMPOSIZIONE: DoSave()");
    
    ComposizioneBean bean = new ComposizioneBean(11, "9781245562344", 1);

    ComposizioneModelDm mock = Mockito.mock(ComposizioneModelDm.class);
        
    Mockito.when(mock.doSave(bean)).thenReturn(1);
    assertEquals(1, mock.doSave(bean)); 
  }

  @Test
    void testDoDelete() throws SQLException {
    System.out.println("Testing :ORDINE: DoDelete()");

    ComposizioneBean bean = new ComposizioneBean(11, "9781245562344", 1);

    ComposizioneModelDm mock = Mockito.mock(ComposizioneModelDm.class);
        
    Mockito.when(mock.doDelete(bean.getIdOrdine())).thenReturn(true);
    assertEquals(true, mock.doDelete(bean.getIdOrdine())); 
  }

  @Test
    void testDoRetrieveByKey() throws SQLException {
    System.out.println("Testing :COMPOSIZIONE: DoRetrieveBeKey");
    
    ComposizioneBean bean = new ComposizioneBean(11, "9781245562344", 1);
    ComposizioneModelDm mock = Mockito.mock(ComposizioneModelDm.class);

    Mockito.when(mock.doRetrieveByKey(11)).thenReturn(bean);
    assertEquals(bean, mock.doRetrieveByKey(bean.getIdOrdine()));  
  }
}
