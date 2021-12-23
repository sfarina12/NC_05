package Test.TestModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Bean.ComposizioneBean;
import Model.ComposizioneModelDm;


class TestClassComposizioneModelDM {

	/**
	 * Classe Test per OrdineModelDm.java.
	 *
	 * @author Farina Simone
	 *
	 */

		@Test
	    void testDoRetrieveAll() throws SQLException {
	    System.out.println("Testing :COMPOSIZIONE: DoRetrieveAll()");

	    ComposizioneModelDm dm=new ComposizioneModelDm();
	    
	    ArrayList<ComposizioneBean> ordini=dm.doRetrieveAll(1);
	    assertNotNull(ordini);
	  }

	  @Test
	    void testDoSave() throws SQLException {
	    System.out.println("Testing :COMPOSIZIONE: DoSave()");

	    ComposizioneModelDm dm=new ComposizioneModelDm();
	    
	    ComposizioneBean exampleOrdine = new ComposizioneBean(1,"isbn",1);

	    int i = dm.doSave(exampleOrdine);

	    dm.doDelete(exampleOrdine.getIdOrdine());

	    assertEquals(i, 1);
	  }
/*
	  @Test
	    void testDoDelete() throws SQLException {
	    System.out.println("Testing :ORDINE: DoDelete()");

	    ComposizioneModelDm dm=new ComposizioneModelDm();
	    
	    ComposizioneBean exampleOrdine = new ComposizioneBean(1,"isbn",1);

	    dm.doSave(exampleOrdine);

	    boolean i=dm.doDelete(exampleOrdine.getIdOrdine());

	    assertEquals(i, false);
	  }
	  
	  @Test
	    void testDoRetrieveByKey() throws SQLException {
	    System.out.println("Testing :COMPOSIZIONE: DoRetrieveBeKey");
	    
	    ComposizioneModelDm dm=new ComposizioneModelDm();
	    
	    ComposizioneBean exampleOrdine = new ComposizioneBean(1,"isbn",1);

	    dm.doSave(exampleOrdine);
	    
	    ComposizioneBean bean = (ComposizioneBean) dm.doRetrieveByKey(1);
	    ComposizioneBean expected =
	        new ComposizioneBean(1,"isbn",1);

	    assertTrue(expected.getIdOrdine()==bean.getIdOrdine()
	        && expected.getIsbn().equals(bean.getIsbn())
	        && expected.getQuantita()==bean.getQuantita());
	    
	    dm.doDelete(bean.getIdOrdine());
	  }*/
	
}
