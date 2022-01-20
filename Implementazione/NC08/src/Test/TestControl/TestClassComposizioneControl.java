package Test.TestControl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;

import Bean.OrdineBean;
import Bean.ProdottoBean;
import Bean.UtenteBean;
import Control.ComposizioneControl;
import Model.OrdineModelDm;
import Model.ProdottoModelDm;

class TestClassComposizioneControl {
  private ComposizioneControl servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private MockHttpSession session;

  @BeforeEach
  void setUp() throws Exception {
    servlet = new ComposizioneControl();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    request.setSession(session);
  }

  @BeforeEach
  public void oneWaySetup() throws ServletException {
    ServletConfig sg = new MockServletConfig();
    servlet.init(sg);
  }
  
  @Test
  void testComposizioneControl() throws SQLException, ServletException, IOException {
    System.out.println("Testing (Control) di ComposizioneControl..");

    ProdottoModelDm prodottoModel = new ProdottoModelDm();
    ProdottoBean prova = prodottoModel.doRetrieveByKey("9788243214531");

    ArrayList<ProdottoBean> lista = new ArrayList<ProdottoBean>();
    lista.add(prova);

    request.getSession().setAttribute("carrello", lista);
    UtenteBean utente = new UtenteBean("asia@dodo.it", "password123", "nickname", false);
    OrdineBean order2 = new OrdineBean("10-12-12", 5, 
            "indirizzo", "asia@dodo.it", true, 10f);
    OrdineModelDm ordineModel = new OrdineModelDm();
    int i = ordineModel.doSave(order2);
    request.getSession().setAttribute("loggedUser", utente);
    
    servlet.doGet(request, response);
    assertEquals("/OrderPage.jsp", response.getForwardedUrl());
    ordineModel.doDelete(order2.getIdOrdine());       
  }
}
