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
import org.springframework.mock.web.MockServletConfig;

import Control.CartControl;
import Control.ShopControl;

// Classe Di Test Non Specificata nel TCS
/**
 * Classe Test per ShopControl.java.
 *
 *@author Alfonso Cuomo
 */
class TestClassShopControl {

  private ShopControl servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  
  @BeforeEach
  void setUp() throws Exception {

    servlet = new ShopControl();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
  }

  @BeforeEach
  public void oneWaySetup() throws ServletException {
    ServletConfig sg = new MockServletConfig();
    servlet.init(sg);
  }
  
  @Test
  public void TestShopControl() throws ServletException, IOException {
    System.out.println("Testing (ShopControl)..");

	  servlet.doGet(request, response);
	  assertEquals("/ShopPage.jsp;", response.getForwardedUrl());
  }

}
