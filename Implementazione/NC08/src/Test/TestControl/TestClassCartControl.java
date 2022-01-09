package Test.TestControl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;

import Bean.ProdottoBean;
import Control.CartControl;
import Model.ProdottoModelDm;

class TestClassCartControl {

  private CartControl servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private MockHttpSession session;

  @BeforeEach
  void setUp() throws Exception {
	  
    servlet = new CartControl();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    request.setSession(session);
  }

	@BeforeEach
	  public void oneWaySetup() throws ServletException {
	    ServletConfig sg = new MockServletConfig();
	    servlet.init(sg);
	  }

	// TC_
		  @Test
		  void testTcRimozioneProdottoCarr() throws IOException, ServletException, SQLException {
		    System.out.println("Testing (AdminControl) -> Rimozione dal carrello di un prodotto..");

		    ProdottoModelDm prodottoModel = new ProdottoModelDm();
	        ProdottoBean prova = prodottoModel.doRetrieveByKey("9788243214531");
	        
	        ArrayList<ProdottoBean> lista = new ArrayList<ProdottoBean>();
	        lista.add(prova);
	        
	        request.getSession().setAttribute("carrello", lista);
		    request.addParameter("action", "delete");
		    request.addParameter("isbn", "9788243214531");

	        servlet.doGet(request, response);
	        assertEquals("/Prodotto.jsp;" , response.getForwardedUrl());
	 }


}
