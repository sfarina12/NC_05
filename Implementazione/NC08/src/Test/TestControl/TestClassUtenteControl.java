package Test.TestControl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

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

import Bean.UtenteBean;
import Control.UtenteControl;
import Model.UtenteModelDm;

class TestClassUtenteControl {

  private UtenteControl servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private MockHttpSession session;

  @BeforeEach
  void setUp() throws Exception {

    servlet = new UtenteControl();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    request.setSession(session);
  }

  @BeforeEach
  public void oneWaySetup() throws ServletException {
    ServletConfig sg = new MockServletConfig();
    servlet.init(sg);
  }

  // TC_1.1_06
  @Test
  void testTcCampiNonCompilati() throws IOException, ServletException {
    System.out.println("Testing (UtenteControl) -> Campi Non Compilati..");

    String oracolo = "i campi sono vuoti";

    request.setAttribute("usrMail", "");
    request.setAttribute("usrPass", "");
    request.setAttribute("registerOrNot", "Y");
    request.setAttribute("logout", "N");
    request.setAttribute("usrNick", "");
    
    IllegalArgumentException exception = 
            assertThrows(IllegalArgumentException.class, () -> {
              servlet.doGet(request, response);
            });
    
    assertEquals(oracolo, exception.getMessage());
  }
  
  @Test
  void testTcEmailNonCorretta() throws IOException, ServletException {
    System.out.println("Testing (UtenteControl) -> Email non rispetta il formato..");

    String oracolo = "l'email non rispetta il formato";

    request.setAttribute("usrMail", "enzo@dodo");
    request.setAttribute("usrPass", "enzo1234");
    request.setAttribute("registerOrNot", "Y");
    request.setAttribute("logout", "N");
    request.setAttribute("usrNick", "eats_dinner");

    IllegalArgumentException exception = 
        assertThrows(IllegalArgumentException.class, () -> {
          servlet.doGet(request, response);
        });

    assertEquals(oracolo, exception.getMessage());
  }
  
  //TC_1.1_07
  @Test
  void testTcEmaiPresente() throws IOException, ServletException {
    System.out.println("Testing (UtenteControl) -> Email già presente nel DB..");

    String oracolo = "email già presente!";

    request.setAttribute("usrMail", "asia@dodo.it");
    request.setAttribute("usrPass", "enzo1234");
    request.setAttribute("registerOrNot", "Y");
    request.setAttribute("logout", "N");
    request.setAttribute("usrNick", "eats_dinner");
    
    IllegalArgumentException exception = 
        assertThrows(IllegalArgumentException.class, () -> {
          servlet.doGet(request, response);
        });

    assertEquals(oracolo, exception.getMessage());
  }
  
//TC_1.1_07
  @Test
  void testTcUtenteRegistratoSuccess() throws IOException, ServletException {
    System.out.println("Testing (UtenteControl) -> Utente registrato con successo..");

    request.setAttribute("usrMail", "uccello@dodo.it");
    request.setAttribute("usrPass", "password123");
    request.setAttribute("registerOrNot", "Y");
    request.setAttribute("logout", "N");
    request.setAttribute("usrNick", "bird23");
    
    String oracolo = "utente Registrato!";
    
    IllegalArgumentException exception = 
            assertThrows(IllegalArgumentException.class, () -> {
              servlet.doGet(request, response);
            });

        assertEquals(oracolo, exception.getMessage());
  }
}