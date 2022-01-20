package Test.TestControl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;

import Control.AdminControl;
import Control.HomeControl;

/**
 * Classe Test per HomeControl.java.
 *
 *@author Alfonso Cuomo
 */
class TestClassHomeControl {

  private HomeControl servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private MockHttpSession session;

  @BeforeEach
  void setUp() throws Exception {
    servlet = new HomeControl();
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
  void testHomeControl() throws ServletException, IOException {
    System.out.println("Testing (TestControl) di HomeControl");
    
    servlet.doPost(request, response);
    assertEquals("/HomePage.jsp", response.getForwardedUrl());        
  }

}
