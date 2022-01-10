package Test.TestControl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.test.annotation.Rollback;

import Control.AutenticazioneFacade;

/**
 * Classe Test per AutenticazioneFacade.java.
 *
 *@author Alfonso Cuomo
 */
class TestClassAutenticazioneFacade {
	
  private AutenticazioneFacade servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  @BeforeEach
  public void setUp() throws SQLException, ParseException {
    servlet = new AutenticazioneFacade();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
  }
	
  @BeforeEach
  public void oneWaySetup() throws ServletException {
    ServletConfig sg = new MockServletConfig();
    servlet.init(sg);
  }

  @Test
  @Rollback
  void redirectUtenteControlTest() 
      throws ServletException, IOException, SQLException, ParseException {
    System.out.println("Testing (Autenticazione Facade) redirect in UtenteControl...");

    request.addParameter("action", "user");
    request.addParameter("usrMail", "mail@dodo.it");
    request.addParameter("usrPass", "password123");
    request.addParameter("registerOrNot", "Y");
    request.addParameter("logout", "N");
    request.addParameter("usrNick", "francesca123");

    servlet.doGet(request, response);
    assertEquals("/UtenteControl", response.getForwardedUrl());
  }

  @Test
  @Rollback
  void redirectShopControl() 
      throws ServletException, IOException, SQLException, ParseException {
    System.out.println("Testing (Autenticazione Facade) redirect in ShopControl...");

    request.setParameter("action", "shop");

    servlet.doGet(request, response);
    assertEquals("/ShopControl", response.getRedirectedUrl());
  }
  
  @Test
  @Rollback
  void redirectCartControl() 
      throws ServletException, IOException, SQLException, ParseException {
    System.out.println("Testing (Autenticazione Facade) redirect in CartControl...");

    request.setParameter("action", "cart");

    servlet.doGet(request, response);
    assertEquals("/CartControl", response.getRedirectedUrl());
  }
  
  @Test
  @Rollback
  void redirectHomeControl() 
      throws ServletException, IOException, SQLException, ParseException {
    System.out.println("Testing (Autenticazione Facade) redirect in HomeControl...");

    request.setParameter("action", "home");

    servlet.doGet(request, response);
    assertEquals("/HomeControl", response.getRedirectedUrl());
  }
  
  @Test
  @Rollback
  void redirectProdottoControl() 
      throws ServletException, IOException, SQLException, ParseException {
    System.out.println("Testing (Autenticazione Facade) redirect in ProdottoControl...");

    request.setParameter("action", "prodotto");
    request.setParameter("isbn", "1234567891234");
    
    servlet.doGet(request, response);
    assertEquals("/Prodotto", response.getForwardedUrl());
  }
}