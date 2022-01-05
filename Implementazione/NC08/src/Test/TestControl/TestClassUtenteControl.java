package Test.TestControl;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
    System.out.println("Testing (UtenteControl) -> Campi Non Compilati()..");

    request.setAttribute("usrMail", "");
    request.setAttribute("usrPass", "");
    request.setAttribute("registerOrNot", "N");
    request.setAttribute("logout", "N");
    request.setAttribute("usrNick", "");
    
    servlet.doGet(request, response);
    assertEquals(response.getForwardedUrl(), "/User/login.jsp");

  }

}
