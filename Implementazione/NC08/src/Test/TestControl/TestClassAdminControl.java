package Test.TestControl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;

import Bean.ProdottoBean;
import Control.AdminControl;
import Control.UtenteControl;
import Model.ProdottoModelDm;

/**
 * Classe Test per AdminControl.java.
 *
 *@author Alfonso Cuomo
 */
class TestClassAdminControl {

  private AdminControl servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private MockHttpSession session;

  @BeforeEach
  void setUp() throws Exception {

    servlet = new AdminControl();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    request.setSession(session);
  }

  @BeforeEach
  public void oneWaySetup() throws ServletException {
    ServletConfig sg = new MockServletConfig();
    servlet.init(sg);
  }
	
  // TC_09.09_05
  @Test
  void testTcIsbnPresente() throws IOException, ServletException, SQLException {
    System.out.println("Testing (AdminControl) -> prodotto già presente (isbn)..");

    String oracolo = "Errore, riprovare";

    request.setAttribute("action", "add");

    ProdottoModelDm prodottoModel = new ProdottoModelDm();
    ProdottoBean prova = prodottoModel.doRetrieveByKey("9788243214531");
    ProdottoBean bean = new ProdottoBean();
    bean.setCopertina(prova.getCopertina());

    request.setAttribute("isbn", "9781245562344");
    request.setAttribute("titolo", "titolo");
    request.setAttribute("autore", "autore");
    request.setAttribute("prezzo", "6");
    request.setAttribute("descrizione", "descrizione");
    request.setAttribute("nomeCategoria", "categoria");
    request.setAttribute("quantitaStock", "54");
    request.setAttribute("file", bean.getCopertina());
    request.setAttribute("errore", "positivo");
	
    servlet.doGet(request, response);
        
    assertEquals(oracolo, request.getAttribute("errore"));
  }

  //TC_09.09_03
  @Test
  void testTcPrezzoErrato() throws IOException, ServletException, SQLException {
    System.out.println("Testing (AdminControl) -> prodotto non inserito:Prezzo errato..");

    String oracolo = "Errore, riprovare";

    request.setAttribute("action", "add");

    ProdottoModelDm prodottoModel = new ProdottoModelDm();
    ProdottoBean prova = prodottoModel.doRetrieveByKey("9788243214531");
    ProdottoBean bean = new ProdottoBean();
    bean.setCopertina(prova.getCopertina());

    request.setAttribute("isbn", "9758445542343");
    request.setAttribute("titolo", "titolo");
    request.setAttribute("autore", "autore");
    request.setAttribute("prezzo", "6a");
    request.setAttribute("descrizione", "descrizione");
    request.setAttribute("nomeCategoria", "categoria1");
    request.setAttribute("quantitaStock", "54");
    request.setAttribute("file", bean.getCopertina());
    request.setAttribute("errore", "positivo");
	
    servlet.doGet(request, response);
        
    assertEquals(oracolo, request.getAttribute("errore"));
  }

  //TC_09.09_07
  @Test
  void testTcInserimentoProdotto() throws IOException, ServletException, SQLException {
    System.out.println("Testing (AdminControl) -> prodotto inserito correttamente..");

    request.setAttribute("action", "add");

    ProdottoModelDm prodottoModel = new ProdottoModelDm();
    ProdottoBean prova = prodottoModel.doRetrieveByKey("9788243214531");
    ProdottoBean bean = new ProdottoBean();
    bean.setCopertina(prova.getCopertina());

    request.setAttribute("isbn", "9758445542343");
    request.setAttribute("titolo", "titolo");
    request.setAttribute("autore", "autore");
    request.setAttribute("prezzo", "6");
    request.setAttribute("descrizione", "descrizione");
    request.setAttribute("nomeCategoria", "categoria1");
    request.setAttribute("quantitaStock", "54");
    request.setAttribute("file", bean.getCopertina());
    request.setAttribute("errore", "positivo");

    servlet.doGet(request, response);
    bean = prodottoModel.doRetrieveByKey("9758445542343");
    assertEquals(bean.getIsbn(), "9758445542343");
    prodottoModel.doDelete("9758445542343");
  }
}
