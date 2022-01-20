package Test.TestControl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

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

import Control.AcquistoControl;
import Control.AdminProxy;

class TestClassAdminProxy {
  private AdminProxy servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private MockHttpSession session;	

  @BeforeEach
  void setUp() throws Exception {
    servlet = new AdminProxy();
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
  void testAdminProxySessionNull() throws SQLException, ServletException, IOException {
    System.out.println("Testing (Control) di AdminProxySessionNull..");
    
    request.getSession().setAttribute("role", null);
    String oracolo = "Errore, riprovare";
    
    request.setAttribute("errore", "positivo");
    servlet.doGet(request, response);
    
    assertEquals(oracolo, request.getAttribute("errore"));
  }
  
  @Test
  void testAdminProxySessionNormal() throws SQLException, ServletException, IOException {
    System.out.println("Testing (Control) di AdminProxySessionNormal..");
    
    request.getSession().setAttribute("role", "normal");
    String oracolo = "Errore, riprovare";

    servlet.doGet(request, response);
    
    assertEquals(oracolo, request.getAttribute("errore"));
  }

  @Test
  void testAdminProxyDelete() throws SQLException, ServletException, IOException {
    System.out.println("Testing (Control) di AdminProxySessionDelete..");
    
    request.setParameter("action", "delete");
    request.getSession().setAttribute("role", "admin");
    request.setParameter("isbn", "1234567891234");
    
    servlet.doGet(request, response);
    assertEquals("/AdminControl", response.getForwardedUrl());
  } 
  
}    

